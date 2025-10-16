package com.example.gfg_blr_9.services;

import com.example.gfg_blr_9.models.Student;
import com.example.gfg_blr_9.models.StudentInfo;
import com.example.gfg_blr_9.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    StudentRepository repository;


    @Autowired
    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public Student createStudent(StudentInfo studentInfo){
        Student student = new Student();
        student.setFirstName(studentInfo.getFirstName());
        student.setLastName(studentInfo.getLastName());
        return repository.save(student);
    }

    public Student findStudentById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Student> findByFirstName(String firstName){
        return repository.findByFirstName(firstName);
    }
}
