package com.basic.basic.service;

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

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(String id, Student updated) {
        Student existing = getById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());

        return repository.save(existing);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
