package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Response.VariantResponse;
import com.oppo.oppo.Entities.Variants;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantMapper {
    VariantResponse toVariantResponse(Variants variant);
}
