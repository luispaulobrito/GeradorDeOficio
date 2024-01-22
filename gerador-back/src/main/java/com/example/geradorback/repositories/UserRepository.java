package com.example.geradorback.repositories;

import com.example.geradorback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {
    User findByLogin(String login);
}
