package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.request.MessageDtoRequest;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.entity.MessageEntity;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MessageMapper {
    @Autowired
    public MessageMapper(AppUserRepository appUserRepository, ExceptionCheckingUtil exceptionCheckingUtil) {
        this.appUserRepository = appUserRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
    }
    private final AppUserRepository appUserRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    public MessageEntity messageDtoToMessageEntity(MessageDtoRequest messageDtoRequest) throws UserNotFoundException {
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(messageDtoRequest.getToWhom());
        exceptionCheckingUtil.checkForPresentUser(optionalAppUser, messageDtoRequest.getToWhom());
        AppUserEntity appUserEntity = optionalAppUser.get();
        List<AppUserEntity> appUserEntities = new ArrayList<>();
        appUserEntities.add(appUserEntity);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity
                .setContent(messageDtoRequest.getContent())
                .setTitle(messageDtoRequest.getTitle())
                .setAppUserEntities(appUserEntities);
        return messageEntity;
    }
}
