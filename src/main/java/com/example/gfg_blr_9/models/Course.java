package com.example.gfg_blr_9.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@Entity
//@Table(name = "courses")
//@Data
public class Course {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @ManyToMany(mappedBy = "courseList")
    private List<Student> students;

}
