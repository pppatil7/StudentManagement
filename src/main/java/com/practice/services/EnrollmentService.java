package com.practice.services;

import com.practice.dto.ApiResponse;
import com.practice.dto.CreateEnrollmentDto;
import com.practice.dto.EnrollmentDto;

import java.util.Map;

public interface EnrollmentService {

    EnrollmentDto createEnrollment(CreateEnrollmentDto dto);

    ApiResponse updatePartialEnrollmentByEnrollmentId(Long enrollmentId, Map<String, Object> updates);

}
