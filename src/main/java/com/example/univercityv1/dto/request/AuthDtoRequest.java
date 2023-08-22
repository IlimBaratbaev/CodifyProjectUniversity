package com.example.univercityv1.dto.request;

public class AuthDtoRequest {
    private String username;
    private String password;

    public AuthDtoRequest() {
    }

    public String getUsername() {
        return username;
    }

    public AuthDtoRequest setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public AuthDtoRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
