package com.example.universityv2.service;

import com.example.universityv2.dto.request.AppUserDtoRequest;
import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.UserException;
import com.example.universityv2.exception.UserNotFoundException;

public interface AppUserService {

    AppUserEntity getUserByLogin(String login) throws UserNotFoundException, InvalidCredentialsException;
    AppUserEntity registerNewUser(AppUserDtoRequest appUserDtoRequest) throws InvalidCredentialsException, UserException;
}
