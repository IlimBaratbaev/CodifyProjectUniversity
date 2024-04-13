package com.example.universityv2.dto.response;

public class DepartmentDtoResponse {
    public DepartmentDtoResponse() {
    }

    private String title;
    private Long id;

    public String getTitle() {
        return title;
    }

    public DepartmentDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DepartmentDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
