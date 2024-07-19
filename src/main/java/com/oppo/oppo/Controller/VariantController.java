package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.DTO.Response.VariantResponse;
import com.oppo.oppo.Entities.Variants;
import com.oppo.oppo.Service.MobilePhoneService;
import com.oppo.oppo.Service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(name = "smallPrice", required = false, defaultValue = "") Integer smallPrice,
            @RequestParam(name = "bigPrice", required = false, defaultValue = "") Integer bigPrice,
            @RequestParam(name = "ram", required = false, defaultValue = "") Integer ram,
            @RequestParam(name = "rom", required = false, defaultValue = "") Integer rom,
            @RequestParam(name = "charge", required = false, defaultValue = "") Integer charge
    ) {

        return variantService.getVariants(field,pageNumber,pageSize,sort, smallPrice, bigPrice,ram,rom,charge);
    }

}
