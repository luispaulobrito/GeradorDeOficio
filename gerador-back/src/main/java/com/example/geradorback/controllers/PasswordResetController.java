package com.example.geradorback.controllers;

import com.example.geradorback.services.PasswordResetService;
import com.example.geradorback.services.records.RequestPasswordDTO;
import com.example.geradorback.services.records.ResetPasswordRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        passwordResetService.verifyToken(userId, resetToken);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestBody @Validated ResetPasswordRequestDTO resetRequest) {
        passwordResetService.resetPassword(resetRequest);
        return ResponseEntity.ok().build();
    }

}
