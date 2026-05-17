package com.fabricio.personal_finance_api.controller;

import java.math.BigDecimal;

import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/amount-type")
    public ResponseEntity<BigDecimal> calculateAmountByType(@RequestParam TransactionType type) {
        BigDecimal amount = reportService.calculateAmountByType(type);
        return ResponseEntity.ok(amount);
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<BigDecimal> calculateBalance() {
        BigDecimal balance = reportService.calculateBalance();
        return ResponseEntity.ok(balance);
    }
}
