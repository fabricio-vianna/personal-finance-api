package com.fabricio.personal_finance_api.service;

import java.util.List;
import java.util.Optional;

import com.fabricio.personal_finance_api.dto.TransactionDTO;
import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Transaction create(Transaction obj) {
        return repository.save(obj);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> obj = repository.findById(id);
        return obj.get();
    }

    public Transaction update(Transaction obj) {
        Transaction newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private void updateData(Transaction newObj, Transaction obj) {
        newObj.setDescription(obj.getDescription());
        newObj.setAmount(obj.getAmount());
        newObj.setType(obj.getType());
        newObj.setUserId(obj.getUserId());
        newObj.setCategoryId(obj.getCategoryId());
    }

    public Transaction fromDto(TransactionDTO objDto) {
        Transaction transaction = new Transaction();

        transaction.setId(objDto.getId());
        transaction.setDescription(objDto.getDescription());
        transaction.setAmount(objDto.getAmount());
        transaction.setType(objDto.getType());
        transaction.setUserId(objDto.getUserId());
        transaction.setCategoryId(objDto.getCategoryId());

        return transaction;
    }
}
