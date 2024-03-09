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
import org.springframework.stereotype.Service;

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
        emailService.sendPasswordResetEmail(user.getId(), user.getLogin(), token.getToken());
        tokenRepository.save(token);
        logger.info("E-mail de redefinição de senha iniciado para o usuário: {}", requestPasswordDTO.login());
        return true;
    }
}
