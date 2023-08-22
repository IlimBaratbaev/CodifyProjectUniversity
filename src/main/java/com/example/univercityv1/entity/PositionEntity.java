package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class PositionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "position_title")
    private String title;

    public PositionEntity() {
    }

    public Long getId() {
        return id;
    }

    public PositionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PositionEntity setTitle(String title) {
        this.title = title;
        return this;
    }
}
