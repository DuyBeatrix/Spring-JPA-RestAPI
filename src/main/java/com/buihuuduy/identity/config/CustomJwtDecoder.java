package com.buihuuduy.identity.config;

import java.text.ParseException;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;
import com.buihuuduy.identity.DTO.request.IntrospectRequest;
import com.buihuuduy.identity.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder
{
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Autowired
    private AuthService authenticationService;

    // Decode and verify a JWT
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    /**
     * This class is to decodes and validates the provided JWT token.
     * @param token String token of user
     * @return the decoded JWT
     * @throws JwtException
     */
    @Override
    public Jwt decode(String token) throws JwtException
    {
        // Introspect the token using the authentication service
        try {
            var response = authenticationService.introspect(IntrospectRequest.builder().token(token).build());

            if (!response.isSuccess()) throw new JwtException("Token invalid");
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        // Initialize NimbusJwtDecoder lazily with secret key and HS512 algorithm if it is null
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
