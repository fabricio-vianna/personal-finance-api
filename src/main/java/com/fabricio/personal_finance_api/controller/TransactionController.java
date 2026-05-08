package com.fabricio.personal_finance_api.controller;

import java.net.URI;
import java.util.List;

import com.fabricio.personal_finance_api.dto.TransactionDTO;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionDTO> create(@Valid @RequestBody TransactionDTO objDto) {
        Transaction obj = service.fromDto(objDto);
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll() {
        List<Transaction> list = service.findAll();
        List<TransactionDTO> listDto = list.stream().map(x -> new TransactionDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable Long id) {
        Transaction obj = service.findById(id);
        return ResponseEntity.ok(new TransactionDTO(obj));
    }
}
