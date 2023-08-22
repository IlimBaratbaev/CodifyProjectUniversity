package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.MessageDtoRequest;
import com.example.univercityv1.dto.response.MessageDtoResponse;
import com.example.univercityv1.entity.MessageEntity;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.MapstructMapper;
import com.example.univercityv1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "message")
public class MessageController {
    private final MessageService messageService;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public MessageController(MessageService messageService, MapstructMapper mapstructMapper) {
        this.messageService = messageService;
        this.mapstructMapper = mapstructMapper;
    }

    @PreAuthorize("hasAnyRole('RECTOR_SEC')")
    @PostMapping(value = "check")
    MessageEntity sendMessageNonEnrollment(
            @RequestParam(value = "login") String login,
            @AuthenticationPrincipal UserDetails userDetails
                                           ) throws UserNotFoundException {
        String username = userDetails.getUsername();
        return this.messageService.sendMessageNonEnrollment(username, login);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "send")
    MessageDtoResponse sendMessage(
            @RequestBody MessageDtoRequest messageDtoRequest
            ) throws UserNotFoundException {

        MessageEntity message = this.messageService.sendMessage(messageDtoRequest);
        return this.mapstructMapper.mapMessageToDtoResponse(message);
    }
}
