package com.example.universityv2.controller;

import com.example.universityv2.dto.request.MessageDtoRequest;
import com.example.universityv2.dto.response.MessageDtoResponse;
import com.example.universityv2.entity.MessageEntity;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.MapstructMapper;
import com.example.universityv2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MessageEntity> sendMessageNonEnrollment(
            @RequestParam(value = "login") String login,
            @AuthenticationPrincipal UserDetails userDetails
                                           ) throws UserNotFoundException {
        String username = userDetails.getUsername();
        return new ResponseEntity<>(this.messageService.sendMessageNonEnrollment(username, login), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "send")
    public ResponseEntity<MessageDtoResponse> sendMessage(
            @RequestBody MessageDtoRequest messageDtoRequest
            ) throws UserNotFoundException {

        MessageEntity message = this.messageService.sendMessage(messageDtoRequest);
        return new ResponseEntity<>(this.mapstructMapper.mapMessageToDtoResponse(message), HttpStatus.OK);
    }
}
