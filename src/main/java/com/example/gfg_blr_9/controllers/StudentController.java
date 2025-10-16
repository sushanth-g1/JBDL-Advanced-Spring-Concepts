package com.example.gfg_blr_9.controllers;

import com.example.gfg_blr_9.models.Student;
import com.example.gfg_blr_9.models.StudentInfo;
import com.example.gfg_blr_9.services.StudentService;
import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/createStudent") public Student createStudent(@RequestBody StudentInfo studentInfo){
        return studentService.createStudent(studentInfo);
    }
    @GetMapping("/student")
    public Student getStudents(@RequestParam(name = "id") Long studentId){
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam(name = "name") String firstName){
        return studentService.findByFirstName(firstName);
    }

}
