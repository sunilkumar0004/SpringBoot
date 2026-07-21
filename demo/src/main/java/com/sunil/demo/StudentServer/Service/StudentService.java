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
import java.util.regex.Pattern;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public CreateStudentResponseDTO studentValidate(
            CreateStudentRequestDTO createStudentRequestDTO) {

        if (createStudentRequestDTO == null
                || createStudentRequestDTO.getName() == null
                || createStudentRequestDTO.getName().trim().isEmpty()
                || createStudentRequestDTO.getAge() <= 0
                || createStudentRequestDTO.getDepartment() == null
                || createStudentRequestDTO.getDepartment().trim().isEmpty()
                || createStudentRequestDTO.getEmail() == null
                || !EMAIL_PATTERN.matcher(createStudentRequestDTO.getEmail()).matches()) {

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

        if (!EMAIL_PATTERN.matcher(updateStudentRequestDTO.getEmail()).matches()) {
            return null;
        }

        existingStudent.setName(updateStudentRequestDTO.getName());
        existingStudent.setAge(updateStudentRequestDTO.getAge());
        existingStudent.setEmail(updateStudentRequestDTO.getEmail());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return new UpdateStudentResponseDTO(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getAge(),
                savedStudent.getEmail(),
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

    private Student mapToStudent(CreateStudentRequestDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return student;
    }

    private CreateStudentResponseDTO mapToResponseDTO(Student student) {

        return new CreateStudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail(),
                student.getDepartment(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }
}