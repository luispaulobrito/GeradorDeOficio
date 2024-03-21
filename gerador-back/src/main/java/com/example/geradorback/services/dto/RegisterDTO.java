package com.example.geradorback.services.dto;

import com.example.geradorback.services.enums.UserRole;

public record RegisterDTO(String id, String login, String password, UserRole role) {
}
