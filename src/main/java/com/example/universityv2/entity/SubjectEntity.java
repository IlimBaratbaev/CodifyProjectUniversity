package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class SubjectEntity {
    public SubjectEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "subject_name")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_subject_speciality",
            joinColumns = @JoinColumn(name = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SpecialityEntity> specialityEntities;

    @ManyToMany
    @JoinTable(
            name = "m2m_subject_teacher",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<TeacherEntity> teacherEntities;
    public Long getId() {
        return id;
    }

    public SubjectEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SubjectEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<SpecialityEntity> getSpecialityEntities() {
        return specialityEntities;
    }

    public SubjectEntity setSpecialityEntities(List<SpecialityEntity> specialityEntities) {
        this.specialityEntities = specialityEntities;
        return this;
    }

    public List<TeacherEntity> getTeacherEntities() {
        return teacherEntities;
    }

    public void setTeacherEntities(List<TeacherEntity> teacherEntities) {
        this.teacherEntities = teacherEntities;
    }
}
