package com.example.studentadmission.service;

import com.example.studentadmission.model.Course;
import com.example.studentadmission.model.Student;
import com.example.studentadmission.repo.CourseRepo;
import com.example.studentadmission.repo.StudentRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    public List<Student> retrieveAllStudents() {
        return studentRepo.findAll();
    }

    public List<Course> retrieveAllCourses() {
        return courseRepo.findAll();
    }

    public Course retrieveCourse(@NotNull Long courseId) {
        return courseRepo.findAll().stream().filter(student -> student.getId().equals(courseId)).findAny().orElse(null);
    }

    public Student retrieveStudent(Long studentId) {
        return studentRepo.findAll().stream().filter(student -> student.getId().equals(studentId)).findAny().orElse(null);
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(Long.getLong(studentId));

        return student == null ? null : student.getCourses();
    }

    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    public void addStudent(Student student) {
        studentRepo.save(student);
    }

    public ResponseEntity admission(@Valid @NotNull Long studentId, @Valid @NotNull Long courseId) {
        Student student = retrieveStudent(studentId);
        Course course = retrieveCourse(courseId);

        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }

        List<Course> courses = student.getCourses();
        courses.add(course);
        student.setCourses(courses);

        studentRepo.save(student);

        return new ResponseEntity<>("Admission successful", HttpStatus.OK);
    }
}
