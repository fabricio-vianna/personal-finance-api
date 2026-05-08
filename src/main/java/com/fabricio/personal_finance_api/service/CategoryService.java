package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

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

    public Category findById(Long id) {
        Optional<Category> obj = respository.findById(id);
        return obj.get();
    }

    public List<Category> findAll() {
        return respository.findAll();
    }

    public void delete(Long id) {
        findById(id);
        respository.deleteById(id);
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
