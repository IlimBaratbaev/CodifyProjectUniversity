package com.example.universityv2.controller;

import com.example.universityv2.dto.request.AuthDtoRequest;
import com.example.universityv2.dto.response.TokenDtoResponse;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    private final UserDetailsService userDetailsService;
    @Autowired
    public AuthController(AuthService authService, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public TokenDtoResponse login (
            @RequestBody AuthDtoRequest authDto
    ) throws InvalidCredentialsException {
        return this.authService.login(authDto.getUsername(), authDto.getPassword());
    }
}
