package com.buihuuduy.identity.service;

import com.buihuuduy.identity.DTO.request.AuthenRequest;
import com.buihuuduy.identity.DTO.request.IntrospectRequest;
import com.buihuuduy.identity.DTO.request.LogoutRequest;
import com.buihuuduy.identity.DTO.request.RefreshRequest;
import com.buihuuduy.identity.DTO.response.AuthenResponse;
import com.buihuuduy.identity.DTO.response.IntrospectResponse;
import com.buihuuduy.identity.entity.InvalidatedToken;
import com.buihuuduy.identity.entity.User;
import com.buihuuduy.identity.exception.AppException;
import com.buihuuduy.identity.exception.ErrorCode;
import com.buihuuduy.identity.repository.InvalidatedTokenRepository;
import com.buihuuduy.identity.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Slf4j
public class AuthService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvalidatedTokenRepository tokenRepository;
    @Value("${jwt.signerKey}")
    private String signerKey;

    /**
     * Performs token introspection to verify the validity of a signed JWT.
     *
     * @param introspectRequest The request containing the token to introspect.
     * @return IntrospectResponse An IntrospectResponse object indicating whether the token is valid.
     * @throws JOSEException If an error occurs during the JWT verification process.
     * @throws ParseException If the JWT parsing encounters an error.
     */
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException
    {
        String token = introspectRequest.getToken();
        boolean isValid = true;
        try {
            verifyToken(introspectRequest.getToken());
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().success(isValid).build();
    }

    /**
     * Authenticates a user based on the provided authentication request.
     *
     * @param authenRequest The authentication request containing username and password.
     * @return AuthenResponse An AuthenResponse object indicating authentication success and providing a JWT token.
     * @throws AppException If the user is not found or authentication fails, appropriate error codes are thrown.
     */
    public AuthenResponse authenticate(AuthenRequest authenRequest)
    {
        User user = userRepository.findByUsername(authenRequest.getUsername());
        if(user == null) throw new AppException(ErrorCode.USER_NOT_FOUND);
        log.info("Password in database: {}", user.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        log.info("Password request: {}", BCrypt.hashpw(authenRequest.getPassword(), BCrypt.gensalt()));

        // Check if the provided password matches the encoded password stored in the database
        boolean canAuthenticate = passwordEncoder.matches(authenRequest.getPassword(), user.getPassword());

        if(!canAuthenticate) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Create an Authentication Response
        AuthenResponse authenResponse = new AuthenResponse();
        authenResponse.setCanAuthenticate(true);
        authenResponse.setToken(generatedToken(user));
        log.info("User name: {}, Token generated: {}", authenRequest.getUsername(), authenResponse.getToken());
        return authenResponse;
    }

    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        var signToken = verifyToken(logoutRequest.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        tokenRepository.save(invalidatedToken);
    }

    public AuthenResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException
    {
        SignedJWT signedJWT = verifyToken(request.getToken());
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
        tokenRepository.save(invalidatedToken);

        User user = userRepository.findByUsername(signedJWT.getJWTClaimsSet().getSubject());
        String newToken = generatedToken(user);
        return AuthenResponse.builder().token(newToken).canAuthenticate(true).build();
    }

    /**
     * Generates a signed JWT (JSON Web Token) for the given username.
     *
     * @param user The user for whom the JWT is being generated.
     * @return String A string representation of the generated JWT.
     * @throws RuntimeException If an error occurs during JWT signing.
     */
    private String generatedToken(User user)
    {
        // Create a JWSHeader
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        // Build JWTClaimsSet
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("buihuuduy.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        // Create Payload from the JWTClaimsSet
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        // Create a JWSObject
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            // Sign the JWSObject and serialize the JWSObject to obtai the final JWT string
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            log.error("Error generating token", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This function is to verifies the provided JWT token.
     *
     * @param token token the JWT token to verify
     * @return the verified SignedJWT object
     * @throws JOSEException
     * @throws ParseException
     */
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException
    {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        // The expiration date from the JWT claims
        Date expDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Verify the JWT using the JWSVerifier
        boolean verified = signedJWT.verify(verifier);

        // Check if the token is invalid or expired
        if(!verified && expDate.after(new Date())) throw new AppException(ErrorCode.UNAUTHENTICATED);

        // Check if token logout
        if(tokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String buildScope(User user)
    {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if(!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });
        return stringJoiner.toString();
    }
}
