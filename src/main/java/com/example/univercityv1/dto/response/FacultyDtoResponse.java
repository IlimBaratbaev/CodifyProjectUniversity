package com.example.univercityv1.dto.response;

public class FacultyDtoResponse {
    public FacultyDtoResponse() {
    }
    private Long id;
    private String title;

    public String getTitle() {
        return title;
    }

    public FacultyDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public FacultyDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
