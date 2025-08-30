package com.practice.controllers;

import com.practice.dto.ApiResponse;
import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("courses")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CreateCourseDto dto) {
        CourseDto courseDto = courseService.createCourse(dto);
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }

//    @PutMapping("courses/{courseId}/students/{studentId}")
//    public ResponseEntity<ApiResponse> assignCourseToStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
//        ApiResponse apiResponse = courseService.assignCourseToStudent(courseId, studentId);
//        return ResponseEntity.ok(apiResponse);
//    }

}
