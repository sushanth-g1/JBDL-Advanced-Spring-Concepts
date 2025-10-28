package com.example.gfg_blr_9.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
@Table(name="Students")
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column (name= "first_name")
    private String firstName;

//    public List<Course> getCourseList() {
//        return courseList;
//    }
//
//    public void setCourseList(List<Course> courseList) {
//        this.courseList = courseList;
//    }
//    @ManyToMany
//    @JoinTable(
//            name = "course_students",
//            joinColumns = @JoinColumn(name = "id",referencedColumnName = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "course_id")
//    )
//    private List<Course> courseList;


    public StudentContact getContact() {
        return contact;
    }

    public void setContact(StudentContact contact) {
        this.contact = contact;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_Id", referencedColumnName = "id")
    private StudentContact contact;
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String lastName;

    public List<StudentAddress> getStudentAddresses() {
        return studentAddresses;
    }

    public void setStudentAddresses(List<StudentAddress> studentAddresses) {
        this.studentAddresses = studentAddresses;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAddress> studentAddresses;
}
