package com.example.geradorback.controllers;

import com.example.geradorback.domain.User;
import com.example.geradorback.infra.security.TokenService;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.records.AuthenticationDTO;
import com.example.geradorback.services.records.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((User) auth.getPrincipal(), data.isRememberEnabled());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (BadCredentialsException be) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", ConstantesUtil.ERROR_TITLE, "message", ConstantesUtil.CREDENCIAIS_INVALIDAS));
        }
    }
}
