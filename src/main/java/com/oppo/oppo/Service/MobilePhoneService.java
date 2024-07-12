package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.MobilePhoneRequest;
import com.oppo.oppo.DTO.Request.UserCreationRequest;
import com.oppo.oppo.DTO.Request.UserUpdateRequest;
import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.DTO.Response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MobilePhoneService {
    public Page<MobilePhoneResponse> getMobilePhones(Pageable pageable);
    
    public Page<MobilePhoneResponse> getMobilePhonesContains(String s, Pageable pageable);

    public MobilePhoneResponse getById(String id);

    public MobilePhoneResponse addMobilePhone(MobilePhoneRequest request);

    public MobilePhoneResponse updateMobilePhone(String mobilePhoneId, MobilePhoneRequest request);

    public void deleteMobilePhone(String mobilePhoneId);
}
