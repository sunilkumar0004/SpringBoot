package com.sunil.demo.StudentServer.Service;

import com.sunil.demo.StudentServer.Repository.StudentRepository;
import com.sunil.demo.StudentServer.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student studentValidate(Student student){

        int id = student.getId();
        String name = student.getName();
        String department = student.getDepartment();
        int age = student.getAge();


        if(id < 0 || name == null || department == null || age < 0){
            return null;
        }

        studentRepository.save(student);
        return student;
    }
    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse( null);
    }

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
    public boolean deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return false;
        }

        studentRepository.delete(student);
        return true;
    }
}
