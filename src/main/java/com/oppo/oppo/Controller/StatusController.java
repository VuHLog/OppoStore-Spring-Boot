package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.StatusResponse;
import com.oppo.oppo.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public ApiResponse<List<StatusResponse>> getStatus(){
        return ApiResponse.<List<StatusResponse>>builder()
                .result(statusService.getStatus())
                .build();
    }
}
