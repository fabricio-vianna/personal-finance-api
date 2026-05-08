package com.fabricio.personal_finance_api.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fabricio.personal_finance_api.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 3270738648080443739L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    @NotNull
    private BigDecimal amount;

    private TransactionType type;

    @JsonIgnore
    private LocalDateTime transactionDate;

    private Long userId;
    private Long categoryId;

    @PrePersist
    public void prePersist() {
        this.transactionDate = LocalDateTime.now();
    }
}
