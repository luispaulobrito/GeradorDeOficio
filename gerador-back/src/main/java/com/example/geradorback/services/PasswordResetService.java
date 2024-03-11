package com.example.geradorback.services;

import com.example.geradorback.domain.PasswordResetToken;
import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.PasswordResetTokenRepository;
import com.example.geradorback.services.mapper.UserMapper;
import com.example.geradorback.services.records.RegisterDTO;
import com.example.geradorback.services.records.RequestPasswordDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PasswordResetService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LogManager.getLogger(PasswordResetService.class);

    public boolean initiatePasswordReset(RequestPasswordDTO requestPasswordDTO) {
        logger.info("Buscando usuário: {}", requestPasswordDTO.login());
        RegisterDTO registerDTO = userService.findByLogin(requestPasswordDTO);
        if (Objects.isNull(registerDTO)) {
            return false;
        }
        User user = userMapper.toEntity(registerDTO);
        PasswordResetToken token = new PasswordResetToken();
        logger.info("E-mail de redefinição de senha iniciado para o usuário: {}", requestPasswordDTO.login());
        emailService.sendPasswordResetEmail(user.getId(), user.getLogin(), token.getToken());
        tokenRepository.save(token);
        return true;
    }

    public boolean verifyToken(String userId, String resetToken) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.nonNull(user)) {
            PasswordResetToken passwordResetToken = tokenRepository.findByToken(resetToken);
            if (Objects.nonNull(passwordResetToken) &&
                    passwordResetToken.getUser().getId().equals(user.getId()) &&
                    !passwordResetToken.isUtilized() &&
                    !passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public boolean resetPassword(String token, String userId, String newPassword) {
        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token);
        if (Objects.nonNull(passwordResetToken) && !passwordResetToken.isUtilized() && !passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            User user = passwordResetToken.getUser();
            if (user.getId().equals(userId)) {
                user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                passwordResetToken.markAsUtilized();
                tokenRepository.save(passwordResetToken);
                userRepository.save(user);
                logger.info("Senha redefinida com sucesso para o usuário: {}", user.getLogin());
                return true;
            }
        }
        logger.warn("Falha ao redefinir a senha.");
        return false;
    }

}
