package com.example.geradorback.services;

import com.example.geradorback.domain.PasswordResetToken;
import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.PasswordResetTokenRepository;
import com.example.geradorback.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LogManager.getLogger(PasswordResetService.class);

    public void initiatePasswordReset(String login) throws MessagingException {
        logger.info("Buscando usuário: {}", login);
        User user = userRepository.findByLogin(login);

        if (Objects.nonNull(user)) {
            PasswordResetToken token = new PasswordResetToken();
            emailService.sendPasswordResetEmail(user.getLogin(), token.getToken());
            tokenRepository.save(token);
            logger.info("E-mail de redefinição de senha iniciado para o usuário: {}", login);
        }
    }

    public boolean resetPassword(String token) {
        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token);

        if (Objects.nonNull(passwordResetToken) && !passwordResetToken.isUtilized() && !passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            passwordResetToken.markAsUtilized();
            tokenRepository.save(passwordResetToken);
            return true;
        }

        return false;
    }

}
