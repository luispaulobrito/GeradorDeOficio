package com.example.geradorback.domain.records;

import com.example.geradorback.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
