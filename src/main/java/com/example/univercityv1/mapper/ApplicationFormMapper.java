package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.response.ApplicationFormResponse;
import com.example.univercityv1.entity.ApplicationFormEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFormMapper {
    @Autowired
    public ApplicationFormMapper(MapstructMapper mapstructMapper, EntityMapper entityMapper, SpecialtyMapper specialtyMapper) {
        this.mapstructMapper = mapstructMapper;
        this.entityMapper = entityMapper;
        this.specialtyMapper = specialtyMapper;
    }
    private final MapstructMapper mapstructMapper;
    private final EntityMapper entityMapper;
    private final SpecialtyMapper specialtyMapper;
    public ApplicationFormResponse mapApplicationFormToDto(ApplicationFormEntity applicationFormEntity) {
        ApplicationFormResponse result = new ApplicationFormResponse();
        result.setContentText(applicationFormEntity.getContentText())
                .setAppUserDtoResponse(entityMapper.mapAppUserToDto(applicationFormEntity.getAppUserEntity()))
                .setFacultyDtoResponse(mapstructMapper.mapFacultyToDto(applicationFormEntity.getFacultyEntity()))
                .setDepartmentDtoResponse(mapstructMapper.mapDepartmentToDto(applicationFormEntity.getDepartmentEntity()))
                .setSpecialityDtoResponse(specialtyMapper.mapSpecialityToDtoResponse(applicationFormEntity.getSpecialityEntity()))
                .setId(applicationFormEntity.getId())
                .setApproved(applicationFormEntity.getApproved());
        return result;
    }
}
