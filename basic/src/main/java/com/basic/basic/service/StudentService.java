package com.basic.basic.service;

import com.basic.basic.dto.StudentCreateRequest;
import com.basic.basic.dto.StudentResponseDto;
import com.basic.basic.exception.StudentNotFoundException;
import com.basic.basic.mapper.StudentMapper;
import com.basic.basic.model.Student;
import com.basic.basic.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // ----------------------
    // GET ALL
    // ----------------------
    public List<StudentResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // ----------------------
    // GET BY ID
    // ----------------------
    public StudentResponseDto getById(String id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapper.toDto(student);
    }

    // ----------------------
    // CREATE
    // ----------------------
    public StudentResponseDto create(StudentCreateRequest request) {

        Student student = mapper.toEntity(request);
        Student saved = repository.save(student);
        return mapper.toDto(saved);
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
        return mapper.toDto(saved);
    }

    // ----------------------
    // DELETE
    // ----------------------
    public void delete(String id) {
        repository.deleteById(id);
    }
}
