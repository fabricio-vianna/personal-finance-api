package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.CategoryDTO;
import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import com.fabricio.personal_finance_api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository repository;

    @Autowired
    private UserRepository userRepository;

    public Category create(Category obj) {
        return repository.save(obj);
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id " + id));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category update(Category obj) {
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Object not found with id " + id);
        }
    }

    public void updateData(Category newObj, Category obj) {
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setType(obj.getType());
        newObj.setUser(obj.getUser());
    }

    public Category findByUserAndCategory(Long userId, Long categoryId) {
        Optional<Category> obj = repository.findByIdAndUser_Id(categoryId, userId);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));
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
