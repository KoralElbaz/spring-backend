package com.basic.basic.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String id) {
        super("Student with ID " + id + " not found");
    }
}
