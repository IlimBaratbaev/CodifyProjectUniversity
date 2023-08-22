package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "application_form")
public class ApplicationFormEntity {
    public ApplicationFormEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_text")
    private String contentText;
    @Column(name = "approved")
    private Boolean approved;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity departmentEntity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUserEntity appUserEntity;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private FacultyEntity facultyEntity;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private SpecialityEntity specialityEntity;

    public Long getId() {
        return id;
    }

    public ApplicationFormEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContentText() {
        return contentText;
    }

    public ApplicationFormEntity setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public ApplicationFormEntity setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public ApplicationFormEntity setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
        return this;
    }

    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }

    public ApplicationFormEntity setAppUserEntity(AppUserEntity appUserEntity) {
        this.appUserEntity = appUserEntity;
        return this;
    }

    public FacultyEntity getFacultyEntity() {
        return facultyEntity;
    }

    public ApplicationFormEntity setFacultyEntity(FacultyEntity facultyEntity) {
        this.facultyEntity = facultyEntity;
        return this;
    }

    public SpecialityEntity getSpecialityEntity() {
        return specialityEntity;
    }

    public ApplicationFormEntity setSpecialityEntity(SpecialityEntity specialityEntity) {
        this.specialityEntity = specialityEntity;
        return this;
    }
}
