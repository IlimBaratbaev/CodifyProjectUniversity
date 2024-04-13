package com.example.universityv2.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "public", name = "university_group")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "group_name")
    private String title;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private FacultyEntity facultyEntity;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private SpecialityEntity specialityEntity;
    public Long getId() {
        return id;
    }

    public GroupEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GroupEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public GroupEntity setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
        return this;
    }

    public FacultyEntity getFacultyEntity() {
        return facultyEntity;
    }

    public GroupEntity setFacultyEntity(FacultyEntity facultyEntity) {
        this.facultyEntity = facultyEntity;
        return this;
    }

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public GroupEntity setSpecialityEntity(SpecialityEntity specialityEntity) {
        this.specialityEntity = specialityEntity;
        return this;
    }
}
