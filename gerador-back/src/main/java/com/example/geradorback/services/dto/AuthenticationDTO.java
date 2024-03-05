package com.example.geradorback.services.dto;

public record AuthenticationDTO(String login, String password, boolean isRememberEnabled) {
}
