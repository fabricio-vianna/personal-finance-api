package com.fabricio.personal_finance_api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import com.fabricio.personal_finance_api.model.dto.FinancialSummaryDTO;
import com.fabricio.personal_finance_api.model.entity.Transaction;
import com.fabricio.personal_finance_api.model.entity.enums.TransactionType;
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

    public BigDecimal calculateBalance() {
        BigDecimal sumIncome = BigDecimal.ZERO;
        BigDecimal sumExpense = BigDecimal.ZERO;

        List<Transaction> listIncome = repository.findByType(TransactionType.INCOME);
        for (Transaction p : listIncome) {
            sumIncome = sumIncome.add(p.getAmount());
        }

        List<Transaction> listExpense = repository.findByType(TransactionType.EXPENSE);
        for (Transaction p : listExpense) {
            sumExpense = sumExpense.add(p.getAmount());
        }

        return sumIncome.subtract(sumExpense);
    }

    public FinancialSummaryDTO monthlyReport(Integer year, int month) {
        LocalDateTime startDate = LocalDate.of(year, month, 1).atStartOfDay();

        LocalDateTime endDate = startDate.withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<Transaction> list = repository.findByCreatedAtBetween(startDate, endDate);

        BigDecimal totalIncome = list.stream().filter(x -> x.getType() == TransactionType.INCOME).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalExpense = list.stream().filter(x -> x.getType() == TransactionType.EXPENSE).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new FinancialSummaryDTO(Month.of(month).toString(), year, totalIncome, totalExpense, balance);
    }
}
