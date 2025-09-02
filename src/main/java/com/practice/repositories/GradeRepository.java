package com.practice.repositories;

import com.practice.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    boolean existsByCourseSemesterNumberAndAppearedSemesterName(Integer courseSemesterNumber, String appearedSemesterName);

    boolean existsByCourseCourseIdAndStudentStudentId(Long courseId, Long studentId);

}
