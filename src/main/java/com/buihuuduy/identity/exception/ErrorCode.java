package com.buihuuduy.identity.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode
{
    USERNAME_ALREADY_EXITST(409, "Username already exitst"),
    USER_NOT_FOUND(404, "User not found"),
    USER_NAME_INVALID(400, "User name must contain characters"),
    PASSWORD_INVALID(400, "Password must be least 8 characters"),
    FULL_NAME_INVALID(400, "Full name must contain characters"),
    UNAUTHENTICATED(401, "Login unsuccessful"),
    UNAUTHORIZED(403, "You don't have permission to access this resource"),
    DOB_INVALID(400, "Your age must be more than 2 years"),
    ;

    int code;
    String message;
}
