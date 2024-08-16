package com.buihuuduy.identity.mapper;

import com.buihuuduy.identity.DTO.request.RoleRequest;
import com.buihuuduy.identity.DTO.response.RoleResponse;
import com.buihuuduy.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}