package com.example.universityv2.service;

import com.example.universityv2.dto.response.TokenDtoResponse;
import com.example.universityv2.exception.InvalidCredentialsException;

public interface AuthService {
    TokenDtoResponse login(String login, String password) throws InvalidCredentialsException;
}
