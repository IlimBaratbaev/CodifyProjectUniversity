package com.example.universityv2.service;


import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;


    @Spy
    private AppUserEntity user;

    @Test
    public void testRegisterNewUser_OK() {

    }

//    @Test
//    public void getUserByLogin_OK() {
//        try {
//            Mockito
//                    .when(this.appUserRepository.findByLogin(any()))
//                    .thenAnswer(x -> Optional.of(this.appUser()));
//            AppUserService userService = new AppUserServiceImpl(this.appUserRepository, passwordEncoder);
//            AppUserEntity user1 = userService.getUserByLogin("test");
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private AppUserEntity appUser(){
        return new AppUserEntity().setId(2L).setLogin("test").setPassword("test").setName("Ilim").setSurname("Baratbaev");
    }
}