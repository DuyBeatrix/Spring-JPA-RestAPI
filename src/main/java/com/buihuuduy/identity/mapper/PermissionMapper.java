package com.buihuuduy.identity.mapper;

import org.mapstruct.Mapper;
import com.buihuuduy.identity.DTO.request.PermissionRequest;
import com.buihuuduy.identity.DTO.response.PermissionResponse;
import com.buihuuduy.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper
{
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
