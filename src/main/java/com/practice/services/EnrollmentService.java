package com.practice.services;

import com.practice.dto.EnrollmentDto;

public interface EnrollmentService {


    EnrollmentDto createEnrollment(Long courseId, Long studentId);

}
