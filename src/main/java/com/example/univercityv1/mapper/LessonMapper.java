package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.response.*;
import com.example.univercityv1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonMapper {

    @Autowired
    public LessonMapper(EntityMapper entityMapper, MapstructMapper mapstructMapper) {
        this.entityMapper = entityMapper;
        this.mapstructMapper = mapstructMapper;
    }
    private final EntityMapper entityMapper;
    private final MapstructMapper mapstructMapper;
    public ScheduleDtoResponse mapScheduleToDtoResponse (ScheduleEntity scheduleEntity) {
        ScheduleDtoResponse scheduleDtoResponse = new ScheduleDtoResponse();
        LessonEntity lessonEntity = scheduleEntity.getLessonEntity();
        TeacherEntity teacherEntity = lessonEntity.getTeacherEntity();
        scheduleDtoResponse
                .setLessonId(lessonEntity.getId())
                .setId(scheduleEntity.getId())
                .setGroupName(lessonEntity.getGroupEntity().getTitle())
                .setSubjectName(lessonEntity.getSubjectEntity().getTitle())
                .setTeacherFullName(String.format("%s %s", teacherEntity.getEmployeeEntity().getName(), teacherEntity.getEmployeeEntity().getSurname()))
                .setTime(lessonEntity.getLocalDateTime().toString());
        return scheduleDtoResponse;
    }
    public List<ScheduleDtoResponse> mapScheduleToDtoResponses(List<ScheduleEntity> scheduleEntities) {
        List<ScheduleDtoResponse> scheduleDtoResponses = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            scheduleDtoResponses.add(this.mapScheduleToDtoResponse(scheduleEntity));
        }
        return scheduleDtoResponses;
    }

    public LessonDtoResponse mapLessonToDtoResponse (LessonEntity lessonEntity) {
        LessonDtoResponse lessonDtoResponse = new LessonDtoResponse();
        TeacherEntity teacherEntity = lessonEntity.getTeacherEntity();
        lessonDtoResponse
                .setId(lessonEntity.getId())
                .setGroupName(lessonEntity.getGroupEntity().getTitle())
                .setSubjectName(lessonEntity.getSubjectEntity().getTitle())
                .setTeacherFullName(String.format("%s %s", teacherEntity.getEmployeeEntity().getName(), teacherEntity.getEmployeeEntity().getSurname()))
                .setDate(lessonEntity.getLocalDateTime().toString())
                .setLessonStatus(lessonEntity.getLessonStatus());
        return lessonDtoResponse;
    }
    public List<LessonDtoResponse> mapLessonsToDtoResponses(List<LessonEntity> lessonEntities) {
        List<LessonDtoResponse> lessonDtoResponses = new ArrayList<>();
        for (LessonEntity lessonEntity : lessonEntities) {
            lessonDtoResponses.add(this.mapLessonToDtoResponse(lessonEntity));
        }
        return lessonDtoResponses;
    }

    public LessonAttendanceDtoResponse mapAttendanceToDtoResponse(LessonAttendanceEntity lessonAttendanceEntity) {
        LessonAttendanceDtoResponse lessonAttendanceDtoResponse = new LessonAttendanceDtoResponse();
        lessonAttendanceDtoResponse
                .setId(lessonAttendanceEntity.getId())
                .setPresent(lessonAttendanceEntity.getPresent())
                .setLessonDtoResponse(this.mapLessonToDtoResponse(lessonAttendanceEntity.getLessonEntity()))
                .setStudentDtoResponse(entityMapper.mapStudentToDtoResponse(lessonAttendanceEntity.getStudentEntity()));
        return lessonAttendanceDtoResponse;
    }
    public List<LessonAttendanceDtoResponse> mapAttendancesToDtoResponses (List<LessonAttendanceEntity> lessonAttendanceEntities) {
        List<LessonAttendanceDtoResponse> lessonAttendanceDtoResponses = new ArrayList<>();
        for (LessonAttendanceEntity lessonAttendanceEntity : lessonAttendanceEntities) {
            lessonAttendanceDtoResponses.add(this.mapAttendanceToDtoResponse(lessonAttendanceEntity));
        }
        return lessonAttendanceDtoResponses;
    }

    public SubjectDtoResponse mapSubjectToDtoResponse(SubjectEntity subjectEntity) {
        return new SubjectDtoResponse()
                .setId(subjectEntity.getId())
                .setTitle(subjectEntity.getTitle());
    }

    public AssignmentDtoResponse mapAssignmentToDtoResponse(Assignment assignment) {
        AssignmentDtoResponse assignmentDtoResponse = new AssignmentDtoResponse();
        assignmentDtoResponse
                .setId(assignment.getId())
                .setDeadline(assignment.getDeadline())
                .setContent(assignment.getContent())
                .setMaxMark(assignment.getMaxMark())
                .setLessonDtoResponse(this.mapLessonToDtoResponse(assignment.getLessonEntity()));
        return assignmentDtoResponse;
    }
    public List<AssignmentDtoResponse> mapAssignmentsToDtoResponses(List<Assignment> assignments) {
        List<AssignmentDtoResponse> assignmentDtoResponses = new ArrayList<>();
        for (Assignment assignment : assignments) {
            assignmentDtoResponses.add(this.mapAssignmentToDtoResponse(assignment));
        }
        return assignmentDtoResponses;
    }
    public FinishedAssignmentDtoResponse mapFinishedAssignmentToDtoResponse(FinishedAssignment finishedAssignment) {
        FinishedAssignmentDtoResponse finishedAssignmentDtoResponse = new FinishedAssignmentDtoResponse();
         finishedAssignmentDtoResponse
                 .setId(finishedAssignment.getId())
                 .setSolution(finishedAssignment.getSolution())
                 .setAssignmentDtoResponse(this.mapAssignmentToDtoResponse(finishedAssignment.getAssignment()))
                 .setStudentDtoResponse(entityMapper.mapStudentToDtoResponse(finishedAssignment.getStudentEntity()));
        return finishedAssignmentDtoResponse;
    }
    public List<FinishedAssignmentDtoResponse> mapFinishedAssignmentsToDtoResponses(List<FinishedAssignment> finishedAssignments) {
        List<FinishedAssignmentDtoResponse> result = new ArrayList<>();
        for (FinishedAssignment finishedAssignment : finishedAssignments) {
            result.add(this.mapFinishedAssignmentToDtoResponse(finishedAssignment));
        }
        return result;
    }

    public MarkDtoResponse mapMarkToDtoResponse(MarkEntity markEntity) {
        MarkDtoResponse markDtoResponse = new MarkDtoResponse()
                .setMark(markEntity.getMark())
                .setId(markEntity.getId())
                .setStudentDtoResponse(entityMapper.mapStudentToDtoResponse(markEntity.getStudentEntity()))
                .setFinishedAssignmentDtoResponse(this.mapFinishedAssignmentToDtoResponse(markEntity.getFinishedAssignment()));
        return markDtoResponse;
    }
}
