package com.example.univercityv1.dto.response;

public class GroupDtoResponse {
    public GroupDtoResponse() {
    }
    private String title;
    private Long id;

    public String getTitle() {
        return title;
    }

    public GroupDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GroupDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
