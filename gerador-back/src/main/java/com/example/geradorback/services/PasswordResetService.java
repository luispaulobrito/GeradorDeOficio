package com.example.geradorback.services;

import com.example.geradorback.domain.PasswordResetToken;
import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.PasswordResetTokenRepository;
import com.example.geradorback.services.dto.RegisterDTO;
import com.example.geradorback.services.dto.RequestPasswordDTO;
import com.example.geradorback.services.dto.ResetPasswordRequestDTO;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.error.NegocioException;
import com.example.geradorback.services.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
        PasswordResetToken token = new PasswordResetToken(user);
        logger.info("E-mail de redefinição de senha iniciado para o usuário: {}", requestPasswordDTO.login());
        emailService.sendPasswordResetEmail(user.getId(), user.getLogin(), token.getToken());
        tokenRepository.save(token);
        return true;
    }


    public boolean verifyToken(String userId, String resetToken) throws NegocioException {
        RegisterDTO user = Optional.ofNullable(userService.findById(userId)).orElseThrow(() -> new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.TOKEN_INVALIDO));
            PasswordResetToken passwordResetToken = tokenRepository.findByToken(resetToken);
            if (Objects.nonNull(passwordResetToken) &&
                    passwordResetToken.getUser().getId().equals(user.id()) &&
                    !passwordResetToken.isUtilized() &&
                    !passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                return true;
            }
        throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.TOKEN_INVALIDO);
    }

    public boolean resetPassword(ResetPasswordRequestDTO resetRequest) {
        Optional<PasswordResetToken> passwordResetTokenOptional = Optional.ofNullable(tokenRepository.findByToken(resetRequest.token()));
        if (passwordResetTokenOptional.isPresent() && !passwordResetTokenOptional.get().isUtilized() &&
                !passwordResetTokenOptional.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            PasswordResetToken passwordResetToken = passwordResetTokenOptional.get();
            User user = passwordResetToken.getUser();
            if (user.getId().equals(resetRequest.userId())) {
                user.setPassword(new BCryptPasswordEncoder().encode(resetRequest.password()));
                passwordResetToken.markAsUtilized();
                tokenRepository.save(passwordResetToken);
                userService.updateUser(userMapper.toDto(user));
                logger.info("Senha redefinida com sucesso para o usuário: {}", user.getLogin());
                return true;
            }
        }
        logger.warn("Falha ao redefinir a senha.");
        throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.ERRO_REDEFINIR_SENHA);
    }
}
