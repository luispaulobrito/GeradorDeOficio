package com.example.geradorback.services.records;

public record ResetPasswordRequestDTO(String token, String userId, String password) {
}
