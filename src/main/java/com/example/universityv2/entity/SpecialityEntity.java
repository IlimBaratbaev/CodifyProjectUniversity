package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "speciality")
public class SpecialityEntity {
    public SpecialityEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "speciality_title")
    private String specialityTitle;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity departmentEntity;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_subject_speciality",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private List<SubjectEntity> subjectEntities;

    public Long getId() {
        return id;
    }

    public SpecialityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSpecialityTitle() {
        return specialityTitle;
    }

    public SpecialityEntity setSpecialityTitle(String specialityTitle) {
        this.specialityTitle = specialityTitle;
        return this;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public SpecialityEntity setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
        return this;
    }

    public List<SubjectEntity> getSubjectEntities() {
        return subjectEntities;
    }

    public SpecialityEntity setSubjectEntities(List<SubjectEntity> subjectEntities) {
        this.subjectEntities = subjectEntities;
        return this;
    }
}
