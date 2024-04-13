package com.example.universityv2.dto.response;

import com.example.universityv2.entity.PositionEntity;

public class EmployeeDtoResponse {


    private Boolean isDeleted;
    private PositionEntity positionEntity;

    private String name;

    private String surname;

    public EmployeeDtoResponse() {
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public EmployeeDtoResponse setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeDtoResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeeDtoResponse setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public EmployeeDtoResponse setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
        return this;
    }
}
