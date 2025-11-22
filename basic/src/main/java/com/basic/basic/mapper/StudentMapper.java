package com.basic.basic.mapper;

import com.basic.basic.dto.StudentCreateRequest;
import com.basic.basic.dto.StudentResponseDto;
import com.basic.basic.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // DTO -> Entity
    Student toEntity(StudentCreateRequest dto);

    // Entity -> DTO
    StudentResponseDto toDto(Student student);
}
