package com.fabricio.personal_finance_api.service;

import java.math.BigDecimal;
import java.util.List;

import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository repository;

    public BigDecimal calculateAmountByType(TransactionType type) {
        BigDecimal sum = BigDecimal.ZERO;

        List<Transaction> list = repository.findByType(type);

        for (Transaction p : list) {
            sum = sum.add(p.getAmount());
        }

        return sum;
    }
}
