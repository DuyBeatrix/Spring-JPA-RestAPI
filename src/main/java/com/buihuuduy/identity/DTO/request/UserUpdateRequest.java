package com.buihuuduy.identity.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest
{
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @NotBlank(message = "FULL_NAME_INVALID")
    String fullName;
    LocalDate dob;
    Set<String> roles;
}
