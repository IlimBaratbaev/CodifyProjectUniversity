package com.example.univercityv1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    public DepartmentEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "department_title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private FacultyEntity facultyEntity;


    public Long getId() {
        return id;
    }

    public DepartmentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DepartmentEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public FacultyEntity getFacultyEntity() {
        return facultyEntity;
    }

    public DepartmentEntity setFacultyEntity(FacultyEntity facultyEntity) {
        this.facultyEntity = facultyEntity;
        return this;
    }
}
