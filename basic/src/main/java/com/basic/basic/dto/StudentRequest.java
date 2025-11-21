package com.basic.basic.dto;

public class StudentRequest {

    private String name;
    private String email;
    private int age;

    public StudentRequest() {}

    public StudentRequest(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // getters
    public String getName() { return name; }

    public String getEmail() { return email; }

    public int getAge() { return age; }
}
