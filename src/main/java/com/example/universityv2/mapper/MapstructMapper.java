package com.example.universityv2.mapper;

import com.example.universityv2.dto.response.*;
import com.example.universityv2.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapstructMapper {
    FacultyDtoResponse mapFacultyToDto(FacultyEntity facultyEntity);
    DepartmentDtoResponse mapDepartmentToDto(DepartmentEntity departmentEntity);
    SpecialityDtoResponse mapSpecialityToDto(SpecialityEntity specialityEntity);
    MessageDtoResponse mapMessageToDtoResponse(MessageEntity messageEntity);
    List<SubjectDtoResponse> mapSubjectsToDtoResponses(List<SubjectEntity> subjectEntities);
    SubjectDtoResponse mapSubjectToDtoResponse(SubjectEntity subjectEntity);

}
