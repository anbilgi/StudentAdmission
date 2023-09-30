package com.example.studentadmission.repo;

import com.example.studentadmission.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
