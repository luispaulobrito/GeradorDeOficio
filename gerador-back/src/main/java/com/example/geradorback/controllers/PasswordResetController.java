package com.example.geradorback.controllers;

import com.example.geradorback.services.PasswordResetService;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.records.RequestPasswordDTO;
import com.example.geradorback.services.records.ResetPasswordRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Boolean> initiatePasswordReset(@RequestBody @Validated RequestPasswordDTO requestPasswordDTO) throws MessagingException {
        passwordResetService.initiatePasswordReset(requestPasswordDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reset/{userId}/{resetToken}")
    public ResponseEntity verifyToken(@Validated @PathVariable String userId, @PathVariable String resetToken) {
        if (passwordResetService.verifyToken(userId, resetToken)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ConstantesUtil.ERROR_TITLE, "message", ConstantesUtil.TOKEN_INVALIDO));
        }
    }
    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestBody @Validated ResetPasswordRequestDTO resetRequest) {
        if (passwordResetService.resetPassword(resetRequest.token(), resetRequest.userId(), resetRequest.password())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ConstantesUtil.ERROR_TITLE, "message", ConstantesUtil.ERRO_REDEFINIR_SENHA));
        }
    }

}
