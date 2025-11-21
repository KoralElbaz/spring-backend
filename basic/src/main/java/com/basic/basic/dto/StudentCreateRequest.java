package com.basic.basic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentCreateRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(16)
    @Max(120)
    private int age;

    public StudentCreateRequest() {}

    public StudentCreateRequest(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getters
    public String getName() { return name; }

    public String getEmail() { return email; }

    public int getAge() { return age; }
}
