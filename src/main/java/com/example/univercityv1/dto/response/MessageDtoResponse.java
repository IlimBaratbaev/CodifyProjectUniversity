package com.example.univercityv1.dto.response;

public class MessageDtoResponse {
    public MessageDtoResponse() {
    }

    private Long id;
    private String content;
    private String title;

    public Long getId() {
        return id;
    }

    public MessageDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageDtoResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }
}
