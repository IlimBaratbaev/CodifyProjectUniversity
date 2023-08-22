package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.AuthDtoRequest;
import com.example.univercityv1.dto.response.TokenDtoResponse;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.service.AuthService;
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
