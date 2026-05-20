package com.fabricio.personal_finance_api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fabricio.personal_finance_api.entity.Transaction;
import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -180145505532167396L;

    private Long id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private Long categoryId;
    private String categoryName;

    public TransactionResponseDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.createdAt = transaction.getCreatedAt();
        this.updatedAt = transaction.getUpdatedAt();
        this.userId = transaction.getUser().getId();
        this.categoryId = transaction.getCategory().getId();
        this.categoryName = transaction.getCategory().getName();
    }
}
