package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.TransactionDTO;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.User;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.repository.CategoryRespository;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import com.fabricio.personal_finance_api.repository.UserRepository;
import com.fabricio.personal_finance_api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

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

    public List<Transaction> findByCategoryId(Long id) {
        categoryRespository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + id));

        return repository.findByCategoryId(id);
    }

    public List<Transaction> findByType(TransactionType type) {
        return repository.findByType(type);
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
        newObj.setCategoryId(obj.getCategoryId());
    }

    public Transaction findByUserAndTransaction(Long userId, Long transactionId) {

        repository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found with id " + userId));
        repository.findById(transactionId).orElseThrow(() -> new ObjectNotFoundException("Transaction not found with id " + transactionId));


        Optional<Transaction> obj = repository.findByIdAndUser_Id(transactionId, userId);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Transaction not found with this user"));
    }

    public Transaction fromDto(TransactionDTO objDto) {
        Transaction transaction = new Transaction();

        transaction.setId(objDto.getId());
        transaction.setDescription(objDto.getDescription());
        transaction.setAmount(objDto.getAmount());
        transaction.setType(objDto.getType());

        User user = userRepository.findById(objDto.getId()).orElseThrow(() -> new ObjectNotFoundException("User not found with id " + objDto.getId()));

        transaction.setUser(user);
        transaction.setCategoryId(objDto.getCategoryId());

        return transaction;
    }
}
