package com.example.univercityv1.dto.response;

public class OrderDtoResponse {
    public OrderDtoResponse() {

    }

    private Long id;
    private String content;
    private Boolean isDone;
    private EmployeeDtoResponse fromWhom;
    private EmployeeDtoResponse toWhom;

    public Long getId() {
        return id;
    }

    public OrderDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrderDtoResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getDone() {
        return isDone;
    }

    public OrderDtoResponse setDone(Boolean done) {
        isDone = done;
        return this;
    }

    public EmployeeDtoResponse getFromWhom() {
        return fromWhom;
    }

    public OrderDtoResponse setFromWhom(EmployeeDtoResponse fromWhom) {
        this.fromWhom = fromWhom;
        return this;
    }

    public EmployeeDtoResponse getToWhom() {
        return toWhom;
    }

    public OrderDtoResponse setToWhom(EmployeeDtoResponse toWhom) {
        this.toWhom = toWhom;
        return this;
    }
}
