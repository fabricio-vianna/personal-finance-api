package com.fabricio.personal_finance_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.model.dto.TransactionRequestDTO;
import com.fabricio.personal_finance_api.model.entity.Category;
import com.fabricio.personal_finance_api.model.entity.Transaction;
import com.fabricio.personal_finance_api.model.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import com.fabricio.personal_finance_api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private CategoryRespository categoryRespository;

    @Autowired
    private UserRepository userRepository;

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

    public Transaction findByIdAndUser(Long id, Long userId) {
        Transaction obj = findById(id);
        if (!obj.getUser().getId().equals(userId)) {
            throw new ObjectNotFoundException("Object not found with id " + id);
        }
        return obj;
    }

    public Transaction update(Transaction obj, Long userId) {
        Transaction newObj = findByIdAndUser(obj.getId(), userId);
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Long id, Long userId) {
        Transaction obj = findByIdAndUser(id, userId);
        repository.deleteById(obj.getId());
    }

    private void updateData(Transaction newObj, Transaction obj) {
        newObj.setDescription(obj.getDescription());
        newObj.setAmount(obj.getAmount());
        newObj.setType(obj.getType());
        newObj.setUser(obj.getUser());
        newObj.setCategory(obj.getCategory());
    }

    public Transaction findByUserAndTransaction(Long userId, Long transactionId) {

        userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found with id " + userId));

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
