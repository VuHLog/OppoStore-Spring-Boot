package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Response.StatusResponse;
import com.oppo.oppo.Entities.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusResponse toStatusResponse(Status status);
}
