package com.fabricio.personal_finance_api.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import com.fabricio.personal_finance_api.dto.TransactionRequestDTO;
import com.fabricio.personal_finance_api.dto.TransactionResponseDTO;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.service.ReportService;
import com.fabricio.personal_finance_api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<TransactionRequestDTO> create(@Valid @RequestBody TransactionRequestDTO objDto) {
        Transaction obj = service.fromDto(objDto);
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> findAll() {
        List<Transaction> list = service.findAll();
        List<TransactionResponseDTO> listDto = list.stream().map(x -> new TransactionResponseDTO(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {
        Transaction obj = service.findById(id);
        return ResponseEntity.ok(new TransactionResponseDTO(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@RequestBody TransactionRequestDTO objDto, @PathVariable Long id) {
        Transaction obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/categories/{categoryId}")
    public ResponseEntity<List<TransactionResponseDTO>> findByCategoryId(@PathVariable Long categoryId) {
        List<Transaction> obj = service.findByCategoryId(categoryId);
        List<TransactionResponseDTO> listDto = obj.stream().map(x -> new TransactionResponseDTO(x)).toList();

        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/type")
    public ResponseEntity<List<TransactionResponseDTO>> findByType(@RequestParam TransactionType type) {
        List<Transaction> list = service.findByType(type);
        List<TransactionResponseDTO> listDto = list.stream().map(x -> new TransactionResponseDTO(x)).toList();

        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/created-at")
    public ResponseEntity<List<TransactionResponseDTO>> findByCreatedAtBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        List<Transaction> list = service.findByDate(start, end);
        List<TransactionResponseDTO> listDto = list.stream().map(x -> new TransactionResponseDTO(x)).toList();

        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "updated-at")
    public ResponseEntity<List<TransactionResponseDTO>> findByUpdatedAtBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        List<Transaction> list = service.findByUpdatedDate(start, end);
        List<TransactionResponseDTO> listDto = list.stream().map(x -> new TransactionResponseDTO(x)).toList();

        return ResponseEntity.ok(listDto);
    }
}
