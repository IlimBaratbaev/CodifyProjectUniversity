package com.example.univercityv1.dto.request;

public class MessageDtoRequest {
    private String content;
    private String title;
    private String toWhom;

    public MessageDtoRequest() {
    }

    public String getContent() {
        return content;
    }

    public MessageDtoRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getToWhom() {
        return toWhom;
    }

    public MessageDtoRequest setToWhom(String toWhom) {
        this.toWhom = toWhom;
        return this;
    }
}
