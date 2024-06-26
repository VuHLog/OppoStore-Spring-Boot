package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Response.RoleResponse;
import com.oppo.oppo.Entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse(Role role);
}
