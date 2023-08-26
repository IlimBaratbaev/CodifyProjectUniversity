package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.AppUserDtoRequest;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.UserException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.repository.AppRoleRepository;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.repository.PositionRepository;
import com.example.univercityv1.service.AppUserService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExceptionCheckingUtil exceptionCheckingUtil;

    @Autowired
    public AppUserServiceImpl(AppUserRepository userRepository, PasswordEncoder passwordEncoder, ExceptionCheckingUtil exceptionCheckingUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.exceptionCheckingUtil = exceptionCheckingUtil;
    }

    @Override
    public AppUserEntity getUserByLogin(String login) throws UserNotFoundException, InvalidCredentialsException {
        exceptionCheckingUtil.checkForEmptiness(login);
        Optional<AppUserEntity> optionalUser=  userRepository.findByLogin(login);
        exceptionCheckingUtil.checkForPresentUser(optionalUser, login);
        return optionalUser.get();
    }

    @Override
    public AppUserEntity registerNewUser(AppUserDtoRequest appUserDtoRequest) throws InvalidCredentialsException, UserException {
        exceptionCheckingUtil.checkForEmptiness(appUserDtoRequest.getLogin());
        exceptionCheckingUtil.checkForEmptiness(appUserDtoRequest.getName());
        exceptionCheckingUtil.checkForEmptiness(appUserDtoRequest.getSurname());
        exceptionCheckingUtil.checkForEmptiness(appUserDtoRequest.getPassword());
        List<AppUserEntity> userEntities = userRepository.findAll();
        for (AppUserEntity appUserEntity : userEntities) {
            if (appUserEntity.getLogin().equals(appUserDtoRequest.getLogin())) {
                throw new UserException("Пользователь с логином " + appUserDtoRequest.getLogin() + " уже существует");
            }
        }
        AppUserEntity appUserEntity = new AppUserEntity()
                .setName(appUserDtoRequest.getName())
                .setSurname(appUserDtoRequest.getSurname())
                .setLogin(appUserDtoRequest.getLogin())
                .setPassword(this.passwordEncoder.encode(appUserDtoRequest.getPassword()));
        userRepository.save(appUserEntity);
        return appUserEntity;
    }
}
