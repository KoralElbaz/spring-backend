package com.basic.basic.service;

import com.basic.basic.dto.StudentCreateRequest;
import com.basic.basic.dto.StudentResponseDto;
import com.basic.basic.model.Student;
import com.basic.basic.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // ----------------------
    // GET ALL
    // ----------------------
    public List<StudentResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    // ----------------------
    // GET BY ID
    // ----------------------
    public StudentResponseDto getById(String id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return toResponseDto(student);
    }

    // ----------------------
    // CREATE
    // ----------------------
    public StudentResponseDto create(StudentCreateRequest request) {

        Student student = new Student(
                request.getName(),
                request.getEmail(),
                request.getAge()
        );

        Student saved = repository.save(student);
        return toResponseDto(saved);
    }

    // ----------------------
    // UPDATE
    // ----------------------
    public StudentResponseDto update(String id, StudentCreateRequest updated) {

        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());

        Student saved = repository.save(existing);
        return toResponseDto(saved);
    }

    // ----------------------
    // DELETE
    // ----------------------
    public void delete(String id) {
        repository.deleteById(id);
    }

    // ----------------------
    // MAPPER: MODEL → DTO
    // ----------------------
    private StudentResponseDto toResponseDto(Student s) {
        return new StudentResponseDto(
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getAge()
        );
    }
}
