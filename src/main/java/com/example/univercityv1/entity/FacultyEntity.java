package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
public class FacultyEntity {
    public FacultyEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "faculty_title")
    private String title;

    public Long getId() {
        return id;
    }

    public FacultyEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FacultyEntity setTitle(String title) {
        this.title = title;
        return this;
    }
}
