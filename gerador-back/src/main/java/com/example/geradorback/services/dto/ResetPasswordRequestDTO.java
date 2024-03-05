package com.example.geradorback.services.dto;

public record ResetPasswordRequestDTO(String token, String userId, String password) {
}
