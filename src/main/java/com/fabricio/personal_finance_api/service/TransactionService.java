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

    public Transaction fromDto(TransactionDTO objDto) {
        Transaction transaction = new Transaction();

        transaction.setId(objDto.getId());
        transaction.setDescription(objDto.getDescription());
        transaction.setAmount(objDto.getAmount());
        transaction.setType(objDto.getType());
        transaction.setTransactionDate(objDto.getTransactionDate());
        transaction.setUserId(objDto.getUserId());
        transaction.setCategoryId(objDto.getCategoryId());

        return transaction;
    }
}
