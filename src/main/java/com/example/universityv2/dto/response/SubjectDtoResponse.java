package com.example.universityv2.dto.response;

public class SubjectDtoResponse {
    public SubjectDtoResponse() {
    }

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public SubjectDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SubjectDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }
}
