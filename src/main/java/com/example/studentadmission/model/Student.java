package com.example.studentadmission.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.List;

@Entity
public class Student {
    private Long id;
    @NotBlank
    private String studentName;

    private List<Course> courses;

    public Student(Long id, String studentName, List<Course> courses) {
        this.id = id;
        this.studentName = studentName;
        this.courses = courses;
    }

    public Student() {
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Course> getCourses() {
        if (courses == null) {
            return Collections.emptyList();
        }
        else
            return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
