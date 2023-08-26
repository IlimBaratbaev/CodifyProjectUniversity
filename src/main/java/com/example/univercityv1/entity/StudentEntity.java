package com.example.univercityv1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "student")
public class StudentEntity {
    public StudentEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String name;
    @Column(name = "student_surname")
    private String surname;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity groupEntity;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUserEntity appUserEntity;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity facultyEntity;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity specialityEntity;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public StudentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public StudentEntity setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public StudentEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public StudentEntity setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
        return this;
    }

    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }

    public StudentEntity setAppUserEntity(AppUserEntity appUserEntity) {
        this.appUserEntity = appUserEntity;
        return this;
    }

    public FacultyEntity getFacultyEntity() {
        return facultyEntity;
    }

    public StudentEntity setFacultyEntity(FacultyEntity facultyEntity) {
        this.facultyEntity = facultyEntity;
        return this;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public StudentEntity setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
        return this;
    }

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public StudentEntity setSpecialityEntity(SpecialityEntity specialityEntity) {
        this.specialityEntity = specialityEntity;
        return this;
    }

}
