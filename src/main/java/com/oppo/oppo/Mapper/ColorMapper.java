package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.ColorRequest;
import com.oppo.oppo.DTO.Response.ColorResponse;
import com.oppo.oppo.Entities.Colors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Colors toColor(ColorRequest request);

    ColorResponse toColorResponse(Colors color);

    void updateColor(@MappingTarget Colors color, ColorRequest request);
}
