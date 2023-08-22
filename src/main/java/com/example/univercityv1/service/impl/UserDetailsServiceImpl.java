package com.example.univercityv1.service.impl;

import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
