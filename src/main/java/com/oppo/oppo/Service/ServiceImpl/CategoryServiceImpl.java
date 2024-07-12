package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.CategoryRepository;
import com.oppo.oppo.DTO.Response.CategoryResponse;
import com.oppo.oppo.Mapper.CategoryMapper;
import com.oppo.oppo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }
}
