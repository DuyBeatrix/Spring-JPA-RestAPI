package com.buihuuduy.identity.mapper;

import com.buihuuduy.identity.DTO.request.UserCreationRequest;
import com.buihuuduy.identity.DTO.request.UserUpdateRequest;
import com.buihuuduy.identity.DTO.response.UserResponse;
import com.buihuuduy.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
