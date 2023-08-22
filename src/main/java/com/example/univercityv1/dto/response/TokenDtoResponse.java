package com.example.univercityv1.dto.response;

public class TokenDtoResponse {
    public TokenDtoResponse() {
    }
    private String accessToken;
    public String getAccessToken() {
        return accessToken;
    }

    public TokenDtoResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
