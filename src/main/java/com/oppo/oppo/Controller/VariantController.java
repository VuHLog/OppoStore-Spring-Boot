package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.DTO.Response.VariantResponse;
import com.oppo.oppo.Entities.Variants;
import com.oppo.oppo.Service.MobilePhoneService;
import com.oppo.oppo.Service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {
    @Autowired
    private VariantService variantService;

    @GetMapping("")
    public Page<VariantResponse> getVariants(
            @RequestParam(name = "field", required = false, defaultValue = "price") String field,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "searchText", required = false, defaultValue = "") String searchText,
            @RequestParam(name = "smallPrice", required = false, defaultValue = "") Integer smallPrice,
            @RequestParam(name = "bigPrice", required = false, defaultValue = "") Integer bigPrice,
            @RequestParam(name = "ram", required = false, defaultValue = "") Integer ram,
            @RequestParam(name = "rom", required = false, defaultValue = "") Integer rom,
            @RequestParam(name = "charge", required = false, defaultValue = "") Integer charge
    ) {

        return variantService.getVariants(field,pageNumber,pageSize,sort, smallPrice, bigPrice,ram,rom,charge,searchText);
    }

    @GetMapping("/{variantId}")
    public ApiResponse<VariantResponse> getVariantById(@PathVariable String variantId) {
        return ApiResponse.<VariantResponse>builder()
                .result(variantService.getVariantById(variantId))
                .build();
    }

    @GetMapping("/{mobilePhoneId}/{colorId}")
    public ApiResponse<List<VariantResponse>> getVariantByMobilePhone_IdAndColor_Id(@PathVariable String mobilePhoneId, @PathVariable String colorId) {
        return ApiResponse.<List<VariantResponse>>builder()
                .result(variantService.getByMobilePhone_IdAndColor_Id(mobilePhoneId,colorId))
                .build();
    }

}
