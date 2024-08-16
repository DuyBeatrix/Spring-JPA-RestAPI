package com.buihuuduy.identity.controller;

import com.buihuuduy.identity.DTO.request.AuthenRequest;
import com.buihuuduy.identity.DTO.request.IntrospectRequest;
import com.buihuuduy.identity.DTO.request.LogoutRequest;
import com.buihuuduy.identity.DTO.request.RefreshRequest;
import com.buihuuduy.identity.DTO.response.ApiResponse;
import com.buihuuduy.identity.DTO.response.AuthenResponse;
import com.buihuuduy.identity.DTO.response.IntrospectResponse;
import com.buihuuduy.identity.entity.InvalidatedToken;
import com.buihuuduy.identity.repository.InvalidatedTokenRepository;
import com.buihuuduy.identity.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenController
{
    @Autowired
    AuthService authService;

    /**
     * The function is a controller which authenticate user found information login
     * @param authenRequest - AuthenRequest contain username and password
     * @return ApiResponse result of authentication
     */
    @PostMapping("/login")
    public ApiResponse<AuthenResponse> authenticate(@RequestBody AuthenRequest authenRequest)
    {
        AuthenResponse authenResponse = authService.authenticate(authenRequest);
        ApiResponse<AuthenResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(authenResponse);
        return apiResponse;
    }

    /**
     * The function is a controller which check token is valid or invalid
     * @param introspectRequest - IntrospectRequest contain token string
     * @return ApiResponse result of checking
     */
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult(authService.introspect(introspectRequest));
        return apiResponse;
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authService.logout(logoutRequest);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setResult("Logout success");
        return apiResponse;
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authService.refreshToken(request);
        return ApiResponse.<AuthenResponse>builder().result(result).build();
    }
}
