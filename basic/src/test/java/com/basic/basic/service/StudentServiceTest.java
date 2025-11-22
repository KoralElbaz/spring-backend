package com.basic.basic.service;

import com.basic.basic.dto.StudentCreateRequest;
import com.basic.basic.dto.StudentResponseDto;
import com.basic.basic.mapper.StudentMapper;
import com.basic.basic.model.Student;
import com.basic.basic.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class StudentServiceTest {

    private final StudentRepository repository = Mockito.mock(StudentRepository.class);
    private final StudentMapper mapper = Mockito.mock(StudentMapper.class);

    private final StudentService service = new StudentService(repository, mapper);

    @Test
    void testGetAll() {
        Student s1 = new Student("1", "Koral", "koral@mail.com", 25);
        Student s2 = new Student("2", "Dana", "dana@mail.com", 30);

        StudentResponseDto dto1 = new StudentResponseDto("1", "Koral", "koral@mail.com", 25);
        StudentResponseDto dto2 = new StudentResponseDto("2", "Dana", "dana@mail.com", 30);

        Mockito.when(repository.findAll()).thenReturn(List.of(s1, s2));
        Mockito.when(mapper.toDto(s1)).thenReturn(dto1);
        Mockito.when(mapper.toDto(s2)).thenReturn(dto2);

        List<StudentResponseDto> result = service.getAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Koral", result.get(0).getName());
    }

    @Test
    void testGetById() {
        Student s = new Student("1", "Koral", "koral@mail.com", 25);
        StudentResponseDto dto = new StudentResponseDto("1", "Koral", "koral@mail.com", 25);

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(s));
        Mockito.when(mapper.toDto(s)).thenReturn(dto);

        StudentResponseDto result = service.getById("1");

        Assertions.assertEquals("Koral", result.getName());
    }

    @Test
    void testCreate() {
        StudentCreateRequest request = new StudentCreateRequest("Roni", "roni@mail.com", 28);

        Student entity = new Student(null, "Roni", "roni@mail.com", 28);
        Student saved = new Student("10", "Roni", "roni@mail.com", 28);
        StudentResponseDto dto = new StudentResponseDto("10", "Roni", "roni@mail.com", 28);

        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toDto(saved)).thenReturn(dto);

        StudentResponseDto result = service.create(request);

        Assertions.assertEquals("Roni", result.getName());
        Assertions.assertEquals("10", result.getId());
    }

    @Test
    void testDelete() {
        service.delete("100");

        Mockito.verify(repository, Mockito.times(1)).deleteById("100");
    }
}
