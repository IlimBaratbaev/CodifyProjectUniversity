package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.AppUserDtoRequest;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.UserException;
import com.example.univercityv1.exception.UserNotFoundException;

public interface AppUserService {

    AppUserEntity getUserByLogin(String login) throws UserNotFoundException, InvalidCredentialsException;
    AppUserEntity registerNewUser(AppUserDtoRequest appUserDtoRequest) throws InvalidCredentialsException, UserException;
}
