package com.sunil.demo.StudentServer.Controller;

import com.sunil.demo.StudentServer.Entity.Student;
import com.sunil.demo.StudentServer.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> storeStudent(@RequestBody Student student){

        Student result = studentService.studentValidate(student);

        if(result == null){
            return ResponseEntity
                    .status(400)
                    .body("Student info is not valid");
        }

        return ResponseEntity
                .status(201)
                .body(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity
                .status(200)
                .body(student);
    }
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
