package com.example.geradorback.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "password_reset_token")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime expiryDate;

    private boolean utilized;

    public PasswordResetToken() {
        this.token = UUID.randomUUID().toString();
        this.expiryDate = LocalDateTime.now().plusHours(24);
        this.utilized = false;
    }

    public boolean isUtilized() {
        return utilized;
    }

    public void markAsUtilized() {
        this.utilized = true;
    }
}


