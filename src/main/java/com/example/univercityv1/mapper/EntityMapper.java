package com.example.univercityv1.mapper;

import com.example.univercityv1.dto.response.*;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.repository.DepartmentRepository;
import com.example.univercityv1.repository.FacultyRepository;
import com.example.univercityv1.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityMapper {
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public EntityMapper(DepartmentRepository departmentRepository, FacultyRepository facultyRepository, GroupRepository groupRepository, MapstructMapper mapstructMapper) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
        this.groupRepository = groupRepository;
        this.mapstructMapper = mapstructMapper;
    }

    public FacultyDtoResponse mapFacultyToDto(FacultyEntity facultyEntity) {
        String title = facultyEntity.getTitle();
        Long id = facultyEntity.getId();
        return new FacultyDtoResponse().setId(id).setTitle(title);
    }

    public List<FacultyDtoResponse> mapFacultyToDtoList(List<FacultyEntity> facultyEntityList) {
        List<FacultyDtoResponse> result = new ArrayList<>();
        for (FacultyEntity facultyEntity : facultyEntityList) {
            result.add(this.mapFacultyToDto(facultyEntity));
        }
        return result;
    }

    public DepartmentDtoResponse mapDepartmentToDto(DepartmentEntity departmentEntity) {
        String title = departmentEntity.getTitle();
        Long id = departmentEntity.getId();
        return new DepartmentDtoResponse().setId(id).setTitle(title);
    }

    public List<DepartmentDtoResponse> mapDepartmentToDtoList(List<DepartmentEntity> departmentEntityList) {
        List<DepartmentDtoResponse> result = new ArrayList<>();
        for (DepartmentEntity departmentEntity : departmentEntityList) {
            result.add(this.mapDepartmentToDto(departmentEntity));
        }
        return result;
    }

    public GroupDtoResponse mapGroupToDto(GroupEntity groupEntity) {
        String title = groupEntity.getTitle();
        Long id = groupEntity.getId();
        return new GroupDtoResponse().setId(id).setTitle(title);
    }

    public List<GroupDtoResponse> mapGroupToDtoList(List<GroupEntity> groupEntityList) {
        List<GroupDtoResponse> result = new ArrayList<>();
        for (GroupEntity groupEntity : groupEntityList) {
            result.add(this.mapGroupToDto(groupEntity));
        }
        return result;
    }

    public AppUserDtoResponse mapAppUserToDto(AppUserEntity appUserEntity){
        String name = appUserEntity.getName();
        String surname = appUserEntity.getSurname();
        String login = appUserEntity.getLogin();
        List<AppRoleEntity> appRoleEntities = appUserEntity.getAppRoles();
        return new AppUserDtoResponse().setLogin(login).setName(name).setSurname(surname).setRoles(appRoleEntities);
    }
    public List<AppUserDtoResponse> mapAppUserToDtoList(List<AppUserEntity> appUserEntityList) {
        List<AppUserDtoResponse> result = new ArrayList<>();
        for (AppUserEntity appUserEntity : appUserEntityList) {
            result.add(this.mapAppUserToDto(appUserEntity));
        }
        return result;
    }

    public EmployeeDtoResponse mapEmployeeToDto(EmployeeEntity employeeEntity) {
        String name = employeeEntity.getName();
        String surname = employeeEntity.getSurname();
        Boolean isDeleted = employeeEntity.getDeleted();
        PositionEntity positionEntity = employeeEntity.getPositionEntity();
        return new EmployeeDtoResponse().setDeleted(isDeleted).setName(name).setSurname(surname).setPositionEntity(positionEntity);
    }

    public TeacherDtoResponse mapTeacherToDtoResponse(TeacherEntity teacherEntity) {
        Long id = teacherEntity.getId();
        EmployeeDtoResponse employeeDtoResponse = this.mapEmployeeToDto(teacherEntity.getEmployeeEntity());
        List<SubjectDtoResponse> subjectEntities = this.mapstructMapper.mapSubjectsToDtoResponses(teacherEntity.getSubjectEntities());
        return new TeacherDtoResponse().setEmployeeDtoResponse(employeeDtoResponse).setId(id).setSubjectDtoResponses(subjectEntities);
    }
}
