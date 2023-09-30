package com.example.studentadmission.controller;

import com.example.studentadmission.model.Course;
import com.example.studentadmission.model.Student;
import com.example.studentadmission.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.retrieveAllStudents();
    }

    @GetMapping(value = "/courses")
    public List<Course> getCourses() {
        return studentService.retrieveAllCourses();
    }

    @PostMapping(value = "/student")
    public void addStudent(@Valid @NotNull @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping(value = "/course")
    public void addCourse(@Valid @NotNull @RequestBody Course course) {
        studentService.addCourse(course);
    }

    @PostMapping("/admission")
    public ResponseEntity<String> addStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {
        return studentService.admission(studentId, courseId);
    }
}

