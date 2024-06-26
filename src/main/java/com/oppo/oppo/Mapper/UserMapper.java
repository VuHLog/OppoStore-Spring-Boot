package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.UserCreationRequest;
import com.oppo.oppo.DTO.Request.UserUpdateRequest;
import com.oppo.oppo.DTO.Response.UserResponse;
import com.oppo.oppo.Entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "user_roles",ignore = true)
    Users toUser(UserCreationRequest request);

    UserResponse toUserResponse(Users user);

    @Mapping(target = "user_roles", ignore = true)
    void updateUser(@MappingTarget Users user, UserUpdateRequest request);
}
