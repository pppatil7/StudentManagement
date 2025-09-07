package com.practice.controllers;

import com.practice.dto.ApiResponse;
import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.dto.StudentDto;
import com.practice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courseDtoList = courseService.getAllCourses();
        return ResponseEntity.ok(courseDtoList);
    }

    @GetMapping("courses/{courseId}/students")
    public ResponseEntity<List<StudentDto>> getAllStudentsByCourseId(@PathVariable Long courseId) {
        List<StudentDto> studentDtoList = courseService.getAllStudentsByCourseId(courseId);
        return ResponseEntity.ok(studentDtoList);
    }

    @GetMapping("courses/{courseId}/students/active")
    public ResponseEntity<List<StudentDto>> getActiveStudentsByCourseId(@PathVariable Long courseId) {
        List<StudentDto> studentDtoList = courseService.getActiveStudentsByCourseId(courseId);
        return ResponseEntity.ok(studentDtoList);
    }

}
