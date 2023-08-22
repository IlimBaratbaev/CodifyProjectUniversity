package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.MessageDtoRequest;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.entity.MessageEntity;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.MessageMapper;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.repository.MessageRepository;
import com.example.univercityv1.service.MessageService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final AppUserRepository appUserRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    @Autowired
    public MessageServiceImpl(AppUserRepository appUserRepository, ExceptionCheckingUtil exceptionCheckingUtil, MessageRepository messageRepository, MessageMapper messageMapper) {
        this.appUserRepository = appUserRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageEntity sendMessageNonEnrollment(String fromWhom, String toWhom) throws UserNotFoundException {
        Optional<AppUserEntity> optionalAppUser1 = appUserRepository.findByLogin(fromWhom);
        Optional<AppUserEntity> optionalAppUser2 = appUserRepository.findByLogin(toWhom);
        exceptionCheckingUtil.checkForPresentUser(optionalAppUser1, fromWhom);
        exceptionCheckingUtil.checkForPresentUser(optionalAppUser2, toWhom);
        AppUserEntity appUserEntity = optionalAppUser2.get();


        List<AppUserEntity> appUserEntities = new ArrayList<>();
        appUserEntities.add(appUserEntity);
        MessageEntity messageEntity = new MessageEntity()
                .setContent(String.format("%s %s вы не прошли зачисление.", optionalAppUser2.get().getName(), optionalAppUser2.get().getSurname()))
                .setTitle("О зачислении")
                .setAppUserEntities(appUserEntities);

        messageRepository.save(messageEntity);

        appUserEntity.getMessageEntities().add(messageEntity);
        messageEntity.getAppUserEntities().add(appUserEntity);

        appUserRepository.save(appUserEntity);

        return messageEntity;
    }

    @Override
    public MessageEntity sendMessage(MessageDtoRequest messageDtoRequest) throws UserNotFoundException {
        MessageEntity messageEntity = messageMapper.messageDtoToMessageEntity(messageDtoRequest);
        messageRepository.save(messageEntity);
        for (AppUserEntity appUserEntity : messageEntity.getAppUserEntities()) {
            appUserEntity.getMessageEntities().add(messageEntity);
        }
        appUserRepository.saveAll(messageEntity.getAppUserEntities());
        return messageEntity;
    }


}
