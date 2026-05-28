package com.fabricio.personal_finance_api.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import com.fabricio.personal_finance_api.model.entity.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TransactionRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 485047547159969312L;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private TransactionType type;

    @NotNull
    private Long categoryId;
}
