package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Response.VariantResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VariantService {
    public Page<VariantResponse> getVariants(String field, Integer pageNumber, Integer pageSize,String sort,Integer smallPrice, Integer bigPrice,Integer ram,Integer rom,Integer charge,String searchText);
    public VariantResponse getVariantById(String variantId);

    public List<VariantResponse> getByMobilePhone_IdAndColor_Id(String mobilePhoneId, String colorId);
}
