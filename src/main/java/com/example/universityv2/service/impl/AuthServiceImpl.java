package com.example.universityv2.service.impl;

import com.example.universityv2.dto.response.TokenDtoResponse;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.security.JwtTokenHandler;
import com.example.universityv2.service.AuthService;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final SecurityContext securityContext;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenHandler jwtTokenHandler;
    private final UserDetailsService userDetailsService;
    private final ExceptionCheckingUtil exceptionCheckingUtil;


    @Autowired
    public AuthServiceImpl(SecurityContext securityContext, PasswordEncoder passwordEncoder, JwtTokenHandler jwtTokenHandler, UserDetailsService userDetailsService, ExceptionCheckingUtil exceptionCheckingUtil) {
        this.securityContext = securityContext;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenHandler = jwtTokenHandler;
        this.userDetailsService = userDetailsService;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
    }

    @Override
    public TokenDtoResponse login(String login, String password) throws InvalidCredentialsException{ //clean code найти замену tokenResponseDto
        TokenDtoResponse result = new TokenDtoResponse();
        exceptionCheckingUtil.checkForEmptiness(login);
        UserDetails user = this.userDetailsService.loadUserByUsername(login);
        if (this.passwordEncoder.matches(password, user.getPassword())){
            Authentication authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            user,
                            null,
                            user.getAuthorities()
                    );
            return result.setAccessToken(this.jwtTokenHandler.generateToken(authentication));
        }
        throw new InvalidCredentialsException("Неверный логин или пароль.");}
}
