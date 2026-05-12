package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.CategoryDTO;
import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;

    @Autowired
    private UserRepository userRepository;

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

    public Category update(Category obj) {
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        return respository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        respository.deleteById(id);
    }

    public void updateData(Category newObj, Category obj) {
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setType(obj.getType());
        newObj.setUser(obj.getUser());
    }

    public Category fromDto(CategoryDTO objDto) {
        Category category = new Category();

        category.setId(objDto.getId());
        category.setName(objDto.getName());
        category.setType(objDto.getType());

        User user = userRepository.findById(objDto.getUserId()).get();

        category.setUser(user);

        return category;
    }
}
