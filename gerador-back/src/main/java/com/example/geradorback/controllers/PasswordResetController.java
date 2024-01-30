package com.example.geradorback.controllers;

import com.example.geradorback.services.PasswordResetService;
import com.example.geradorback.services.records.RequestPasswordDTO;
import com.example.geradorback.services.records.ResetPasswordRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<Boolean> initiatePasswordReset(@RequestBody @Validated RequestPasswordDTO requestPasswordDTO) throws MessagingException {
        passwordResetService.initiatePasswordReset(requestPasswordDTO.login());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reset/{userId}/{resetToken}")
    public ResponseEntity<String> verifyToken(@Validated @PathVariable String userId, @PathVariable String resetToken) {
        if (passwordResetService.verifyToken(userId, resetToken)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody @Validated ResetPasswordRequestDTO resetRequest) {
        if (passwordResetService.resetPassword(resetRequest.token(), resetRequest.userId(), resetRequest.password())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
