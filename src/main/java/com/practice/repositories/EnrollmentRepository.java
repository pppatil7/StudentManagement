package com.practice.repositories;

import com.practice.entities.Enrollment;
import com.practice.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentStudentId(Long studentId);

    boolean existsByCourseCourseIdAndStudentStudentId(Long courseId, Long studentId);

    boolean existsByStudentStudentId(Long studentId);

    Enrollment findByCourseCourseIdAndStudentStudentId(Long courseId, Long studentId);

}
