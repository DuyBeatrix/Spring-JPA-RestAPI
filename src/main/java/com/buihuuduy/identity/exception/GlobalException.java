package com.buihuuduy.identity.exception;

import com.buihuuduy.identity.DTO.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Objects;

@ControllerAdvice
public class GlobalException
{
    /**
     * The function is to handle AppException catch in the program
     * @param e AppException
     * @return ApiResponse with code, message.
     */
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> appExceptionHandler(AppException e)
    {
        ErrorCode errorCode = e.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * The function is to handle MethodArgumentNotValidException catch in the program
     * @param e MethodArgumentNotValidException
     * @return Error string
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e)
    {
        // Get message from annotation to get the corresponding ErrorCode
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponse> accessDeniedException(AccessDeniedException e)
    {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }
}
