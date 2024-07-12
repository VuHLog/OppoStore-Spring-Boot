package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.CategoryRequest;
import com.oppo.oppo.DTO.Response.CategoryResponse;
import com.oppo.oppo.Entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
