package com.fabricio.personal_finance_api.controller;

import java.math.BigDecimal;

import com.fabricio.personal_finance_api.dto.FinancialSummaryDTO;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fabricio.personal_finance_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/reports")
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

    @GetMapping(value = "/monthly")
    public ResponseEntity<FinancialSummaryDTO> monthlyReport(@RequestParam Integer year, @RequestParam int month) {
        FinancialSummaryDTO obj = reportService.monthlyReport(year, month);
        return ResponseEntity.ok(obj);
    }
}
