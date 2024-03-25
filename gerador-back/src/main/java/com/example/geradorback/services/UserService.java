package com.example.geradorback.services;

import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.UserRepository;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.error.NegocioException;
import com.example.geradorback.services.mapper.UserMapper;
import com.example.geradorback.services.records.RegisterDTO;
import com.example.geradorback.services.records.RequestPasswordDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LogManager.getLogger(PasswordResetService.class);

    public RegisterDTO saveUser(RegisterDTO registerDTO) throws NegocioException {
        if (Objects.nonNull(userRepository.findByLogin(registerDTO.login()))) {
            throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.USUARIO_EXISTE);
        }

        User user = userMapper.toEntity(registerDTO);
        user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.password()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public RegisterDTO findByLogin(RequestPasswordDTO requestPasswordDTO) {
        User user = userRepository.findByLogin(requestPasswordDTO.login());
        return userMapper.toDto(user);
    }
    public RegisterDTO findById(String userId) throws NegocioException{
        User user = userRepository.findById(userId).orElseThrow(() -> new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.USUARIO_NAO_ENCONTRADO));
        return userMapper.toDto(user);
    }
    public void updateUser(RegisterDTO registerDTO) throws NegocioException {
        userRepository.findById(registerDTO.id())
                .map(user -> {
                    User userUpdated = userMapper.toEntity(registerDTO);
                    return userMapper.toDto(userRepository.save(userUpdated));
                })
                .orElseThrow(() -> new NegocioException(ConstantesUtil.ERROR_TITLE,ConstantesUtil.USUARIO_NAO_ENCONTRADO));
    }
}
