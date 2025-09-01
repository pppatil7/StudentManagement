package com.practice.repositories;

import com.practice.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByEnrollmentsCourseCourseId(Long courseId);
}
