package com.oppo.oppo.Controller;


import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.CategoryResponse;
import com.oppo.oppo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getCategories(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getCategories())
                .build();
    }
}
