package com.example.geradorback.services;

import com.example.geradorback.domain.PasswordResetToken;
import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.PasswordResetTokenRepository;
import com.example.geradorback.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LogManager.getLogger(PasswordResetService.class);

    public boolean initiatePasswordReset(String login) {
        logger.info("Buscando usuário: {}", login);
        User user = userRepository.findByLogin(login);
        if (Objects.isNull(user)) {
            return false;
        }
        PasswordResetToken token = new PasswordResetToken();
        emailService.sendPasswordResetEmail(user.getId(), user.getLogin(), token.getToken());
        tokenRepository.save(token);
        logger.info("E-mail de redefinição de senha iniciado para o usuário: {}", login);
        return true;
    }
}
