package com.practice.repositories;

import com.practice.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByEnrollmentStudentStudentId(Long studentId);

    List<Grade> findByEnrollmentStudentStudentIdAndEnrollmentIsActiveTrue(Long studentId);

    List<Grade> findByEnrollmentStudentStudentIdAndCourseSemesterNumberAndEnrollmentIsActiveTrue(Long studentId, Long courseSemesterNumber);

    boolean existsByEnrollmentEnrollmentIdAndCourseSemesterNumber(Long enrollmentId, Integer courseSemesterNumber);

}
