package com.oppo.oppo.Controller;


import com.oppo.oppo.DTO.Response.ColorResponse;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    @Autowired
    private ColorService colorService;

    @GetMapping("")
    public ApiResponse<List<ColorResponse>> getColors(){
        return ApiResponse.<List<ColorResponse>>builder()
                .result(colorService.getColors())
                .build();
    }
}
