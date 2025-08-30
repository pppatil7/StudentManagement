package com.practice.controllers;

import com.practice.dto.ApiResponse;
import com.practice.dto.CreateEnrollmentDto;
import com.practice.dto.EnrollmentDto;
import com.practice.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("enrollments")
    public ResponseEntity<EnrollmentDto> createEnrollment(@RequestBody CreateEnrollmentDto dto) {
        EnrollmentDto enrollmentDto = enrollmentService.createEnrollment(dto);
        return new ResponseEntity<>(enrollmentDto, HttpStatus.CREATED);
    }

    @PatchMapping("enrollments/{enrollmentId}")
    public ResponseEntity<ApiResponse> updatePartialEnrollmentByEnrollmentId(@PathVariable Long enrollmentId, @RequestBody Map<String, Object> updates) {
        ApiResponse apiResponse = enrollmentService.updatePartialEnrollmentByEnrollmentId(enrollmentId, updates);
        return ResponseEntity.ok(apiResponse);
    }


}
