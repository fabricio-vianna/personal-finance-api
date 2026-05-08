package com.fabricio.personal_finance_api.controller;

import java.net.URI;
import java.util.List;

import com.fabricio.personal_finance_api.dto.CategoryDTO;
import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDTO objDto) {
        Category obj = service.fromDto(objDto);
        service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping
    public ResponseEntity<List<CategoryDTO>> findAll(@Valid @RequestBody CategoryDTO objDto) {
        List<Category> list = service.findAll();
        List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }
}
