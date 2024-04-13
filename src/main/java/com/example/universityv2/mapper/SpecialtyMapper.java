package com.example.universityv2.mapper;

import com.example.universityv2.dto.response.SpecialityDtoResponse;
import com.example.universityv2.entity.SpecialityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyMapper {
    @Autowired
    public SpecialtyMapper(MapstructMapper mapstructMapper) {
        this.mapstructMapper = mapstructMapper;
    }
    private final MapstructMapper mapstructMapper;
    public SpecialityDtoResponse mapSpecialityToDtoResponse(SpecialityEntity specialityEntity) {
        SpecialityDtoResponse specialityDtoResponse = new SpecialityDtoResponse();
        specialityDtoResponse.setId(specialityDtoResponse.getId())
                .setSpecialityTitle(specialityEntity.getSpecialityTitle())
                .setSubjectDtoResponses(mapstructMapper.mapSubjectsToDtoResponses(specialityEntity.getSubjectEntities()));
        return specialityDtoResponse;
    }
}
