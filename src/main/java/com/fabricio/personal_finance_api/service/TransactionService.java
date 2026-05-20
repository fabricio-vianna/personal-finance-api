package com.fabricio.personal_finance_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.TransactionRequestDTO;
import com.fabricio.personal_finance_api.entity.Category;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import com.fabricio.personal_finance_api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private CategoryRespository categoryRespository;

    public Transaction create(Transaction obj) {
        return repository.save(obj);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id " + id));
    }

    public Transaction update(Transaction obj) {
        try {
            Transaction newObj = findById(obj.getId());
            updateData(newObj, obj);
            return repository.save(newObj);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Object not found with id " + obj.getId());
        }
    }

    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException("Object not found with id " + id);
        }
    }

    private void updateData(Transaction newObj, Transaction obj) {
        newObj.setDescription(obj.getDescription());
        newObj.setAmount(obj.getAmount());
        newObj.setType(obj.getType());
        newObj.setUser(obj.getUser());
        newObj.setCategory(obj.getCategory());
    }

    public Transaction findByUserAndTransaction(Long userId, Long transactionId) {

        repository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found with id " + userId));
        repository.findById(transactionId).orElseThrow(() -> new ObjectNotFoundException("Transaction not found with id " + transactionId));


        Optional<Transaction> obj = repository.findByIdAndUser_Id(transactionId, userId);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Transaction not found with this user"));
    }

    public List<Transaction> findByCategoryId(Long id) {
        categoryRespository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + id));

        return repository.findByCategoryId(id);
    }

    public List<Transaction> findByType(TransactionType type) {
        return repository.findByType(type);
    }

    public List<Transaction> findByDate(LocalDateTime start, LocalDateTime end) {
        return repository.findByCreatedAtBetween(start, end);
    }

    public List<Transaction> findByUpdatedDate(LocalDateTime start, LocalDateTime end) {
        return repository.findByUpdatedAtBetween(start, end);
    }

    public Transaction fromDto(TransactionRequestDTO objDto) {
        Transaction transaction = new Transaction();

        transaction.setDescription(objDto.getDescription());
        transaction.setAmount(objDto.getAmount());
        transaction.setType(objDto.getType());

        Category category = categoryRespository.findById(objDto.getCategoryId()).orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + objDto.getCategoryId()));

        transaction.setCategory(category);

        return transaction;
    }
}
