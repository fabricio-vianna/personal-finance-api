package com.fabricio.personal_finance_api.repository;

import java.util.Optional;

import com.fabricio.personal_finance_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByIdAndUser_Id(Long transactionId, Long userId);
}
