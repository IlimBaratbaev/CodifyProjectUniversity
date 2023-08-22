package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.MessageDtoRequest;
import com.example.univercityv1.entity.MessageEntity;
import com.example.univercityv1.exception.UserNotFoundException;

public interface MessageService {
    MessageEntity sendMessageNonEnrollment(String fromWhom, String toWhom) throws UserNotFoundException;
    MessageEntity sendMessage(MessageDtoRequest messageDtoRequest) throws UserNotFoundException;
}
