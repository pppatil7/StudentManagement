package com.practice.repositories;

import com.practice.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByEnrollmentsStudentStudentIdAndEnrollmentsIsActiveTrue(Long studentId);
}
