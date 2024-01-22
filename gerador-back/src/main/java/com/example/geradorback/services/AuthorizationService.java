package com.example.geradorback.services;

import com.example.geradorback.domain.User;
import com.example.geradorback.repositories.UserRepository;
import com.example.geradorback.services.error.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(ConstantesUtil.USUARIO_NAO_ENCONTRADO);
        }
        return new User(user.getId(), user.getLogin(), user.getPassword(), user.getRole());
    }
}
