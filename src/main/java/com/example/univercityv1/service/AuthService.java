package com.example.univercityv1.service;

import com.example.univercityv1.dto.response.TokenDtoResponse;
import com.example.univercityv1.exception.InvalidCredentialsException;

public interface AuthService {
    TokenDtoResponse login(String login, String password) throws InvalidCredentialsException;
}
