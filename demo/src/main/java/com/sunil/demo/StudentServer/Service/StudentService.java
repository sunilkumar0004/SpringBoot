package com.sunil.demo.StudentServer.Service;

import com.sunil.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.sunil.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.sunil.demo.StudentServer.Entity.Student;
import com.sunil.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create Student
    public CreateStudentResponseDTO studentValidate(CreateStudentRequestDTO requestDTO) {

        if (requestDTO == null) {
            return null;
        }

        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            return null;
        }

        if (requestDTO.getAge() <= 0) {
            return null;
        }

        Student student = new Student();

        student.setName(requestDTO.getName());
        student.setAge(requestDTO.getAge());
        student.setDepartment(requestDTO.getDepartment());

        Student savedStudent = studentRepository.save(student);

        return new CreateStudentResponseDTO(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getAge(),
                savedStudent.getDepartment()
        );
    }

    // Get Student
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Update Student
    public Student updateStudent(int id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setDepartment(updatedStudent.getDepartment());

        return studentRepository.save(existingStudent);
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.delete(student);
        return true;
    }
}