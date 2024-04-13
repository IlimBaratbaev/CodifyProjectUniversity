package com.example.universityv2.dto.request;

public class EmployeeDtoRequest {
    private String name;
    private String surname;

    public EmployeeDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public EmployeeDtoRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeeDtoRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
