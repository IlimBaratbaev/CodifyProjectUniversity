package com.example.universityv2.dto.request;

public class OrderDtoRequest {
    public OrderDtoRequest() {
    }

    private String content;
    private String toWhomLogin;


    public String getContent() {
        return content;
    }

    public OrderDtoRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public String getToWhomLogin() {
        return toWhomLogin;
    }

    public OrderDtoRequest setToWhomLogin(String toWhomLogin) {
        this.toWhomLogin = toWhomLogin;
        return this;
    }
}
