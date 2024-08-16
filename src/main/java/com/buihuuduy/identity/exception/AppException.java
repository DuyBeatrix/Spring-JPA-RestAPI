package com.buihuuduy.identity.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppException extends RuntimeException
{
    ErrorCode errorCode;
    public AppException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
