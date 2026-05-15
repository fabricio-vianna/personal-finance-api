package com.fabricio.personal_finance_api.controller.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 2348511069269548215L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
