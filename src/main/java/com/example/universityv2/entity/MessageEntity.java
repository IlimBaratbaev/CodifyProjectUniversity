package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "message")
public class MessageEntity {
    public MessageEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "message_content")
    private String content;

    @ManyToMany(mappedBy = "messageEntities")
    private List<AppUserEntity> appUserEntities;

    public Long getId() {
        return id;
    }

    public MessageEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public List<AppUserEntity> getAppUserEntities() {
        return appUserEntities;
    }

    public MessageEntity setAppUserEntities(List<AppUserEntity> appUserEntities) {
        this.appUserEntities = appUserEntities;
        return this;
    }
}
