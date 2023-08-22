package com.example.univercityv1.dto.request;

public class ApplicationFormRequest {
    public ApplicationFormRequest() {
    }
    private String contentText;
    private Long facultyId;
    private Long departmentId;
    private Long specialityId;

    public String getContentText() {
        return contentText;
    }

    public ApplicationFormRequest setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public ApplicationFormRequest setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public ApplicationFormRequest setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public ApplicationFormRequest setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
        return this;
    }

}
