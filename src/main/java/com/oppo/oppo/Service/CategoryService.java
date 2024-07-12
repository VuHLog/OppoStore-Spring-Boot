package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Response.CategoryResponse;
import com.oppo.oppo.Entities.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponse> getCategories();
}
