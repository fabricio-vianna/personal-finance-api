package com.fabricio.personal_finance_api.controller.exception;

import java.time.Instant;

public record ErrorResponseDTO(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public ErrorResponseDTO(int status, String error, String message, String path) {
        this(Instant.now(), status, error, message, path);
    }
}
