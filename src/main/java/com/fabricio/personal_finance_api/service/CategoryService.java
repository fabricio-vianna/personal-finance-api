package com.fabricio.personal_finance_api.service;

import com.fabricio.personal_finance_api.dto.CategoryDTO;
import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;

    public Category create(Category obj) {
        return respository.save(obj);
    }

    public Category fromDto(CategoryDTO objDto) {
        Category category = new Category();

        category.setId(objDto.getId());
        category.setName(objDto.getName());
        category.setType(objDto.getType());
        category.setUserId(objDto.getUserId());

        return category;
    }
}
