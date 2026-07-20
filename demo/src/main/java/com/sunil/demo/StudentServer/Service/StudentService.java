package com.sunil.demo.StudentServer.Service;

import com.sunil.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.sunil.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.sunil.demo.StudentServer.DTO.UpdateStudentRequestDTO;
import com.sunil.demo.StudentServer.DTO.UpdateStudentResponseDTO;
import com.sunil.demo.StudentServer.Entity.Student;
import com.sunil.demo.StudentServer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO studentValidate(
            CreateStudentRequestDTO createStudentRequestDTO) {

        if (createStudentRequestDTO == null ||
                createStudentRequestDTO.getName() == null ||
                createStudentRequestDTO.getName().trim().isEmpty() ||
                createStudentRequestDTO.getAge() <= 0 ||
                createStudentRequestDTO.getDepartment() == null ||
                createStudentRequestDTO.getDepartment().trim().isEmpty()) {

            return null;
        }

        Student student = mapToStudent(createStudentRequestDTO);
        Student savedStudent = studentRepository.save(student);

        return mapToResponseDTO(savedStudent);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public UpdateStudentResponseDTO updateStudent(
            int id,
            UpdateStudentRequestDTO updateStudentRequestDTO) {

        Student existingStudent =
                studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updateStudentRequestDTO.getName());
        existingStudent.setAge(updateStudentRequestDTO.getAge());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return new UpdateStudentResponseDTO(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getAge(),
                savedStudent.getDepartment(),
                "Student information is updated"
        );
    }

    public boolean deleteStudent(int id) {

        Student student =
                studentRepository.findById(id).orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.deleteById(id);
        return true;
    }

    private Student mapToStudent(
            CreateStudentRequestDTO createStudentRequestDTO) {

        Student student = new Student();

        student.setName(createStudentRequestDTO.getName());
        student.setAge(createStudentRequestDTO.getAge());
        student.setDepartment(createStudentRequestDTO.getDepartment());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return student;
    }

    private CreateStudentResponseDTO mapToResponseDTO(Student student) {

        return new CreateStudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getDepartment(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }
}