package com.example.geradorback.controllers;

import com.example.geradorback.services.PasswordResetService;
import com.example.geradorback.services.records.RequestPasswordDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<String> initiatePasswordReset(@RequestBody @Validated RequestPasswordDTO requestPasswordDTO) {
        try {
            passwordResetService.initiatePasswordReset(requestPasswordDTO.login());
            return ResponseEntity.ok("Instruções de redefinição de senha enviadas com sucesso.");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Erro ao enviar e-mail de redefinição de senha.");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody String token) {
        if (passwordResetService.resetPassword(token)) {
            return ResponseEntity.ok("Senha redefinida com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado.");
        }
    }

}
