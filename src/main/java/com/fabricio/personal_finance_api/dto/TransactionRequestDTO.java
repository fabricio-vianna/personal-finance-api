package com.fabricio.personal_finance_api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 485047547159969312L;

    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private Long categoryId;

    public TransactionRequestDTO(Transaction transaction) {
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.categoryId = transaction.getCategoryId();
    }
}
