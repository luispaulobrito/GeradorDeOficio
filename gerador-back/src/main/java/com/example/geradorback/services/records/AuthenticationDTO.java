package com.example.geradorback.services.records;

public record AuthenticationDTO(String login, String password, boolean isRememberEnabled) {
}
