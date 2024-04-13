package com.example.universityv2.service.impl;

import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUserEntity> optionalAppUser =
                this.appUserRepository.findByLogin(username);
        System.out.println(optionalAppUser.get());
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        }
        throw new UsernameNotFoundException("Пользователся с таким логином нет в системе!");
    }

}
