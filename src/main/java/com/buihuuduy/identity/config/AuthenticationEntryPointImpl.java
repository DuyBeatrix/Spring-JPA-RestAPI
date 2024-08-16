package com.buihuuduy.identity.config;

import com.buihuuduy.identity.DTO.response.ApiResponse;
import com.buihuuduy.identity.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

/**
 * This class implements the {@link AuthenticationEntryPoint} interface to handle unauthorized access attempts.
 * When an unauthenticated user tries to access a protected resource, this entry point sends a custom error response.
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint
{
    /**
     * @param request resulted in an AuthenticationException
     * @param response the user agent can be advised of the failure
     * @param authException the exception that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
    {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        // Set content type to application/json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Create an ApiResponse object with error code and message
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        // Convert java Obj to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        // Push content to client
        response.flushBuffer();
    }
}
