package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Response.VariantResponse;
import org.springframework.data.domain.Page;

public interface VariantService {
    public Page<VariantResponse> getVariants(String field, Integer pageNumber, Integer pageSize,String sort,Integer smallPrice, Integer bigPrice,Integer ram,Integer rom,Integer charge);
}
