package com.basic.basic.controller;

import com.basic.basic.dto.StudentCreateRequest;
import com.basic.basic.dto.StudentResponseDto;
import com.basic.basic.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentService service;

    public StudentsController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public StudentResponseDto create(@Valid @RequestBody StudentCreateRequest student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public StudentResponseDto update(@PathVariable String id,@Valid @RequestBody StudentCreateRequest updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
