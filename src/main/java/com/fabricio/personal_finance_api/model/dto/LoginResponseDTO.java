package com.fabricio.personal_finance_api.model.dto;

public record LoginResponseDTO(
        String token,
        String tokenType) {
    public LoginResponseDTO(String token) {
        this(token, "Bearer");
    }
}
