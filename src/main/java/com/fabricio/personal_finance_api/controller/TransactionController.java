package com.fabricio.personal_finance_api.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import com.fabricio.personal_finance_api.model.dto.TransactionRequestDTO;
import com.fabricio.personal_finance_api.model.dto.TransactionResponseDTO;
import com.fabricio.personal_finance_api.model.entity.Transaction;
import com.fabricio.personal_finance_api.model.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.service.AuthorizationService;
import com.fabricio.personal_finance_api.service.ReportService;
import com.fabricio.personal_finance_api.service.TransactionService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AuthorizationService authorizationService;

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
        Long userId = authorizationService.getAuthenticatedUser().getId();
        Transaction obj = service.findByIdAndUser(id, userId);
        return ResponseEntity.ok(new TransactionResponseDTO(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@Valid @RequestBody TransactionRequestDTO objDto, @PathVariable Long id) {
        Long userId = authorizationService.getAuthenticatedUser().getId();
        Transaction obj = service.fromDto(objDto);
        obj.setId(id);
        service.update(obj, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Long userId = authorizationService.getAuthenticatedUser().getId();
        service.delete(id, userId);
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
