package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.response.*;
import com.example.univercityv1.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapstructMapper {
    FacultyDtoResponse mapFacultyToDto(FacultyEntity facultyEntity);
    DepartmentDtoResponse mapDepartmentToDto(DepartmentEntity departmentEntity);
    SpecialityDtoResponse mapSpecialityToDto(SpecialityEntity specialityEntity);
    MessageDtoResponse mapMessageToDtoResponse(MessageEntity messageEntity);
    List<SubjectDtoResponse> mapSubjectsToDtoResponses(List<SubjectEntity> subjectEntities);
}
