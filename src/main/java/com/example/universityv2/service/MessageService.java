package com.example.universityv2.service;

import com.example.universityv2.dto.request.MessageDtoRequest;
import com.example.universityv2.entity.MessageEntity;
import com.example.universityv2.exception.UserNotFoundException;

public interface MessageService {
    MessageEntity sendMessageNonEnrollment(String fromWhom, String toWhom) throws UserNotFoundException;
    MessageEntity sendMessage(MessageDtoRequest messageDtoRequest) throws UserNotFoundException;
}
