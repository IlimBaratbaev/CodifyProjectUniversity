package com.example.universityv2.dto.response;

import com.example.universityv2.entity.AppRoleEntity;

import java.util.List;

public class AppUserDtoResponse {
    public AppUserDtoResponse() {
    }

    private String name;
    private String surname;
    private String login;
    private List<AppRoleEntity> roles;

    public String getName() {
        return name;
    }

    public AppUserDtoResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AppUserDtoResponse setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public AppUserDtoResponse setLogin(String login) {
        this.login = login;
        return this;
    }

    public List<AppRoleEntity> getRoles() {
        return roles;
    }

    public AppUserDtoResponse setRoles(List<AppRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
