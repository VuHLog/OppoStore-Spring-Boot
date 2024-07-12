package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.MobilePhoneRequest;
import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.Entities.MobilePhone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MobilePhoneMapper {
    MobilePhone toMobilePhone(MobilePhoneRequest request);

    MobilePhoneResponse toMobilePhoneResponse(MobilePhone mobilePhone);

    void updateMobilePhone(@MappingTarget MobilePhone mobilePhone, MobilePhoneRequest request);
}
