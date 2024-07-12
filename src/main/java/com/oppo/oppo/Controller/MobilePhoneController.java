package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Request.MobilePhoneRequest;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.MobilePhoneResponse;
import com.oppo.oppo.Service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mobilePhones")
public class MobilePhoneController {
    @Autowired
    private MobilePhoneService mobilePhoneService;

    @GetMapping("")
    public Page<MobilePhoneResponse> getMobilePhones(
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "search", required = false, defaultValue = "") String search
    ) {

        Sort sortable = null;
        if (sort.toUpperCase().equals("ASC")) {
            sortable = Sort.by(field).ascending();
        }
        if (sort.toUpperCase().equals("DESC")) {
            sortable = Sort.by(field).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortable);
        Page<MobilePhoneResponse> mobilePhones = null;
        if (!search.trim().equals("")) {
            mobilePhones = mobilePhoneService.getMobilePhonesContains(search, pageable);
        } else mobilePhones = mobilePhoneService.getMobilePhones(pageable);
        return mobilePhones;
    }

    @GetMapping("/{mobilePhoneId}")
    public ApiResponse<MobilePhoneResponse> getMobilePhone(@PathVariable String mobilePhoneId) {
        return ApiResponse.<MobilePhoneResponse>builder()
                .result(mobilePhoneService.getById(mobilePhoneId))
                .build();
    }

    @PostMapping("")
    public ApiResponse<MobilePhoneResponse> createMobilePhone(@RequestBody MobilePhoneRequest request) {
        return ApiResponse.<MobilePhoneResponse>builder()
                .result(mobilePhoneService.addMobilePhone(request))
                .build();
    }

    @PutMapping("/{mobilePhoneId}")
    public ApiResponse<MobilePhoneResponse> updateMobilePhone(@PathVariable String mobilePhoneId, @RequestBody MobilePhoneRequest request) {
        return ApiResponse.<MobilePhoneResponse>builder()
                .result(mobilePhoneService.updateMobilePhone(mobilePhoneId, request))
                .build();
    }

    @RequestMapping(value = "/{mobilePhoneId}", method = RequestMethod.DELETE)
    public ApiResponse<String> deleteMobilePhone(@PathVariable String mobilePhoneId) {
        mobilePhoneService.deleteMobilePhone(mobilePhoneId);
        return ApiResponse.<String>builder()
                .result("Mobile Phone has been deleted")
                .build();
    }

}
