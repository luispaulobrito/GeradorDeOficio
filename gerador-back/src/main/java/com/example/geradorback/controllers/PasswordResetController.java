package com.example.geradorback.controllers;

import com.example.geradorback.services.PasswordResetService;
import com.example.geradorback.services.error.ConstantesUtil;
import com.example.geradorback.services.dto.RequestPasswordDTO;
import com.example.geradorback.services.dto.ResetPasswordRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Operation(summary = "Iniciar redefinição de senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "E-mail para redefinir senha enviado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao enviar e-mail para redefinição de senha")
    })
    @PostMapping("/forgot")
    public ResponseEntity<Boolean> initiatePasswordReset(@RequestBody @Validated RequestPasswordDTO requestPasswordDTO) throws MessagingException {
        passwordResetService.initiatePasswordReset(requestPasswordDTO.login());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Verificar token de redefinição de senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "400", description = "Token inválido")
    })
    @GetMapping("/reset/{userId}/{resetToken}")
    public ResponseEntity verifyToken(@Validated @PathVariable String userId, @PathVariable String resetToken) {
        if (passwordResetService.verifyToken(userId, resetToken)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ConstantesUtil.ERROR_TITLE, "message", ConstantesUtil.TOKEN_INVALIDO));
        }
    }

    @Operation(summary = "Redefinir senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao redefinir senha")
    })
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
