package com.example.geradorback.controllers;

import com.example.geradorback.services.UserService;
import com.example.geradorback.services.records.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Validated RegisterDTO registerDTO){
        userService.saveUser(registerDTO);
        return ResponseEntity.ok().build();
    }
}
