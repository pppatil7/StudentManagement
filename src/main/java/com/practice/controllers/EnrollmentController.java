package com.practice.controllers;

import com.practice.dto.ApiResponse;
import com.practice.dto.CreateEnrollmentDto;
import com.practice.dto.EnrollmentDto;
import com.practice.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("enrollments")
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() {
        List<EnrollmentDto> enrollmentDtoList = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollmentDtoList);
    }

    @GetMapping("enrollments/{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentNyEnrollmentId(@PathVariable Long enrollmentId) {
        EnrollmentDto enrollmentDto = enrollmentService.getEnrollmentByEnrollmentId(enrollmentId);
        return ResponseEntity.ok(enrollmentDto);
    }

    @PutMapping("enrollments/de")
    public ResponseEntity<ApiResponse> deEnrollStudentByCourseIdAndStudentId(@RequestBody CreateEnrollmentDto dto) {
        ApiResponse apiResponse = enrollmentService.deEnrollStudentByCourseIdAndStudentId(dto);
        return ResponseEntity.ok(apiResponse);
    }


}
