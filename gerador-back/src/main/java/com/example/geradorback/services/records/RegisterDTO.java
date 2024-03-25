package com.example.geradorback.services.records;

import com.example.geradorback.services.enums.UserRole;

public record RegisterDTO(String id, String login, String password, UserRole role) {
}
