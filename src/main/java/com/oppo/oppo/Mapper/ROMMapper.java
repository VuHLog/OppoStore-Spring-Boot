package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.ROMRequest;
import com.oppo.oppo.DTO.Response.ROMResponse;
import com.oppo.oppo.Entities.ROM;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ROMMapper {
    ROM toROM(ROMRequest request);

    ROMResponse toROMResponse(ROM rom);

    void updateROM(@MappingTarget ROM rom, ROMRequest request);
}
