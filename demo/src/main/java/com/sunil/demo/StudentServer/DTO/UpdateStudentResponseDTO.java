package com.sunil.demo.StudentServer.DTO;

public class UpdateStudentResponseDTO {

    private int id;
    private String name;
    private int age;
    private String department;
    private String message;

    public UpdateStudentResponseDTO() {
    }

    public UpdateStudentResponseDTO(
            int id,
            String name,
            int age,
            String department,
            String message) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
