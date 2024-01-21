package com.example.geradorback.repositories;

import com.example.geradorback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository  extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}