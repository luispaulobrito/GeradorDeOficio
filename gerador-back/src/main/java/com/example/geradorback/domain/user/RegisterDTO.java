package com.example.geradorback.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
