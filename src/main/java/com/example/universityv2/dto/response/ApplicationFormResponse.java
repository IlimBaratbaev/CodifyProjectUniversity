package com.example.universityv2.dto.response;

public class ApplicationFormResponse {
    public ApplicationFormResponse() {
    }
    private Long id;
    private String contentText;
    private AppUserDtoResponse appUserDtoResponse;
    private SpecialityDtoResponse specialityDtoResponse;
    private DepartmentDtoResponse departmentDtoResponse;
    private FacultyDtoResponse facultyDtoResponse;
    private Boolean approved;

    public Long getId() {
        return id;
    }

    public ApplicationFormResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContentText() {
        return contentText;
    }

    public ApplicationFormResponse setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public AppUserDtoResponse getAppUserDtoResponse() {
        return appUserDtoResponse;
    }

    public ApplicationFormResponse setAppUserDtoResponse(AppUserDtoResponse appUserDtoResponse) {
        this.appUserDtoResponse = appUserDtoResponse;
        return this;
    }

    public SpecialityDtoResponse getSpecialityDtoResponse() {
        return specialityDtoResponse;
    }

    public ApplicationFormResponse setSpecialityDtoResponse(SpecialityDtoResponse specialityDtoResponse) {
        this.specialityDtoResponse = specialityDtoResponse;
        return this;
    }

    public DepartmentDtoResponse getDepartmentDtoResponse() {
        return departmentDtoResponse;
    }

    public ApplicationFormResponse setDepartmentDtoResponse(DepartmentDtoResponse departmentDtoResponse) {
        this.departmentDtoResponse = departmentDtoResponse;
        return this;
    }

    public FacultyDtoResponse getFacultyDtoResponse() {
        return facultyDtoResponse;
    }

    public ApplicationFormResponse setFacultyDtoResponse(FacultyDtoResponse facultyDtoResponse) {
        this.facultyDtoResponse = facultyDtoResponse;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public ApplicationFormResponse setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }
}
