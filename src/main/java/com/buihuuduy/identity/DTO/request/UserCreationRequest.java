package com.buihuuduy.identity.DTO.request;

import com.buihuuduy.identity.validator.DOBConstraint;
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
public class UserCreationRequest
{
    @NotBlank(message = "USER_NAME_INVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @NotBlank(message = "FULL_NAME_INVALID")
    String fullName;

    @DOBConstraint(min = 2, message = "DOB_INVALID")
    LocalDate dob;
    //Set<RoleRequest> roles;
}
