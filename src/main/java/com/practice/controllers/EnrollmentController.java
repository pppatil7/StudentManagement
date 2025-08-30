package com.practice.controllers;

import com.practice.dto.EnrollmentDto;
import com.practice.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("courses/{courseId}/students/{studentId}/enrollment")
    public ResponseEntity<EnrollmentDto> createEnrollment(@PathVariable Long courseId, @PathVariable Long studentId) {
        EnrollmentDto enrollmentDto = enrollmentService.createEnrollment(courseId, studentId);
        return new ResponseEntity<>(enrollmentDto, HttpStatus.CREATED);
    }


}
