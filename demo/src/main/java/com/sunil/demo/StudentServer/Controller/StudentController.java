package com.sunil.demo.StudentServer.Controller;

import com.sunil.demo.StudentServer.DTO.CreateStudentRequestDTO;
import com.sunil.demo.StudentServer.DTO.CreateStudentResponseDTO;
import com.sunil.demo.StudentServer.Entity.Student;
import com.sunil.demo.StudentServer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping("/create")
    public ResponseEntity<?> storeStudent(@RequestBody CreateStudentRequestDTO requestDTO) {

        CreateStudentResponseDTO responseDTO = studentService.studentValidate(requestDTO);

        if (responseDTO == null) {
            return ResponseEntity
                    .status(400)
                    .body("Student info is not valid");
        }

        return ResponseEntity
                .status(201)
                .body(responseDTO);
    }

    // Get Student
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity
                    .status(404)
                    .body("Student not found");
        }

        return ResponseEntity
                .status(200)
                .body(student);
    }

    // Update Student
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id,
                                           @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(id, student);

        if (updatedStudent == null) {
            return ResponseEntity
                    .status(404)
                    .body("Student not found");
        }

        return ResponseEntity
                .status(200)
                .body(updatedStudent);
    }

    // Delete Student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity
                    .status(404)
                    .body("Student not found");
        }

        return ResponseEntity
                .status(200)
                .body("Student deleted successfully");
    }
}