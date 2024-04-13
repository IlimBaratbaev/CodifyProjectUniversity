package com.example.universityv2.dto.request;

public class AppUserDtoRequest {
    private String name;
    private String surname;
    private String login;
    private String password;

    public AppUserDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public AppUserDtoRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AppUserDtoRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public AppUserDtoRequest setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUserDtoRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
