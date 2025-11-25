package com.example.gfg_blr_9.services;

import com.example.gfg_blr_9.controllers.StudentController;
import com.example.gfg_blr_9.models.Student;
import com.example.gfg_blr_9.models.StudentAddress;
import com.example.gfg_blr_9.models.StudentContact;
import com.example.gfg_blr_9.models.StudentInfo;
import com.example.gfg_blr_9.repositories.StudentContactRepository;
import com.example.gfg_blr_9.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    StudentRepository repository;
    private StudentContactRepository studentContactRepository;


    @Autowired
    public StudentService(StudentRepository repository, StudentContactRepository studentContactRepository){

        this.repository = repository;
        this.studentContactRepository = studentContactRepository;
    }


    public Student createStudent(StudentInfo studentInfo){


        Student student = new Student();
        student.setFirstName(studentInfo.getFirstName());
        student.setLastName(studentInfo.getLastName());
        StudentContact contact = new StudentContact();
        contact.setEmail("email@mail.com");
        contact.setPhone("990202000");
        student.setContact(contact);


        StudentAddress studentAddress = new StudentAddress();
        studentAddress.setCountry("India");
        studentAddress.setZip("560061");
        studentAddress.setStreet("HSR");
        studentAddress.setState("Karnataka");

        StudentAddress permanentAddress = new StudentAddress();
        permanentAddress.setCountry("India");
        permanentAddress.setZip("560061");
        permanentAddress.setStreet("Badohi");
        permanentAddress.setState("UttarPradesh");
        student.setStudentAddresses(List.of(studentAddress, permanentAddress));
        return repository.save(student);
    }

    public Student findStudentById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Student> findByFirstName(String firstName){
        return repository.findByFirstName(firstName);
    }
    public StudentInfo findByContact(Long contactId){
//        Optional<StudentContact> studentContact = studentContactRepository.findById(Long.valueOf(email));
//        if(studentContact.isPresent()){
//            StudentContact studentContactObject = studentContact.get();
//            return studentContactObject.getStudent();
//        }
//        return null;
        StudentContact studentContact = studentContactRepository.findByContactId(contactId);
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.firstName = studentContact.getStudent().getFirstName();
        studentInfo.lastName = studentContact.getStudent().getLastName();

        return studentInfo;
    }
}
