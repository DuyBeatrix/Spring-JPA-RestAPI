package com.buihuuduy.identity.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse
{
    String id;
    String username;
//    String password;
    String fullName;
    LocalDate dob;
    Set<RoleResponse> roles;
}