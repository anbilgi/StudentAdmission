package com.example.studentadmission.repo;

import com.example.studentadmission.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
