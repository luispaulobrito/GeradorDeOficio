package com.example.geradorback.services;

import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.UserRepository;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.error.NegocioException;
import com.example.geradorback.services.mapper.UserMapper;
import com.example.geradorback.services.records.RegisterDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public RegisterDTO saveUser(RegisterDTO registerDTO) throws NegocioException {
        if (Objects.nonNull(userRepository.findByLogin(registerDTO.login()))) {
            throw new NegocioException(ConstantesUtil.ERROR_TITLE, ConstantesUtil.USUARIO_EXISTE);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = userMapper.toEntity(registerDTO);
        user.setPassword(encryptedPassword);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
