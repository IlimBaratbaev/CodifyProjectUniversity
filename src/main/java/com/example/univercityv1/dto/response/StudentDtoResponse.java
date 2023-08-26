package com.example.univercityv1.dto.response;

import com.example.univercityv1.entity.GroupEntity;

public class StudentDtoResponse {
    public StudentDtoResponse() {
    }
    private Long id;
    private String name;
    private String surname;
    private GroupDtoResponse groupDtoResponse;
    private SpecialityDtoResponse specialityDtoResponse;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public StudentDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDtoResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public StudentDtoResponse setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public GroupDtoResponse getGroupDtoResponse() {
        return groupDtoResponse;
    }

    public StudentDtoResponse setGroupDtoResponse(GroupDtoResponse groupDtoResponse) {
        this.groupDtoResponse = groupDtoResponse;
        return this;
    }

    public SpecialityDtoResponse getSpecialityDtoResponse() {
        return specialityDtoResponse;
    }

    public StudentDtoResponse setSpecialityDtoResponse(SpecialityDtoResponse specialityDtoResponse) {
        this.specialityDtoResponse = specialityDtoResponse;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public StudentDtoResponse setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
