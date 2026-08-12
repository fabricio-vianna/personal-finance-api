package com.fabricio.personal_finance_api.controller;

import java.net.URI;
import java.util.List;

import com.fabricio.personal_finance_api.model.dto.CategoryDTO;
import com.fabricio.personal_finance_api.model.dto.TransactionResponseDTO;
import com.fabricio.personal_finance_api.model.dto.UserDTO;
import com.fabricio.personal_finance_api.model.entity.Category;
import com.fabricio.personal_finance_api.model.entity.Transaction;
import com.fabricio.personal_finance_api.model.entity.User;
import com.fabricio.personal_finance_api.service.CategoryService;
import com.fabricio.personal_finance_api.service.TransactionService;
import com.fabricio.personal_finance_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok(new UserDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto, @PathVariable Long id) {
        User obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<CategoryDTO>> findCategories(@PathVariable Long id) {
        User obj = service.findById(id);
        List<Category> list = obj.getCategories();
        List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{userId}/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoriesById(@PathVariable Long userId, @PathVariable Long categoryId) {
        Category obj = categoryService.findByUserAndCategory(userId, categoryId);
        return ResponseEntity.ok(new CategoryDTO(obj));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponseDTO>> findTransactions(@PathVariable Long id) {
        User obj = service.findById(id);
        List<Transaction> list = obj.getTransactions();
        List<TransactionResponseDTO> listDto = list.stream().map(x -> new TransactionResponseDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{userId}/transactions/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> findTransactionsById(@PathVariable Long userId, @PathVariable Long transactionId) {
        Transaction obj = transactionService.findByUserAndTransaction(userId, transactionId);
        return ResponseEntity.ok(new TransactionResponseDTO(obj));
    }
}
