package com.oppo.oppo.Controller;


import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.ROMResponse;
import com.oppo.oppo.Service.ROMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rom")
public class ROMController {
    @Autowired
    private ROMService ROMService;

    @GetMapping("")
    public ApiResponse<List<ROMResponse>> getROM(){
        return ApiResponse.<List<ROMResponse>>builder()
                .result(ROMService.getROMs())
                .build();
    }
}
