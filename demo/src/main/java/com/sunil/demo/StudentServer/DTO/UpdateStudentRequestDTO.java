package com.sunil.demo.StudentServer.DTO;

public class UpdateStudentRequestDTO {

    private String name;
    private int age;

    public UpdateStudentRequestDTO() {
    }

    public UpdateStudentRequestDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}