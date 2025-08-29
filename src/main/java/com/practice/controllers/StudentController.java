package com.practice.controllers;

import com.practice.dto.CreateStudentDto;
import com.practice.dto.StudentDto;
import com.practice.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("students/")
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentDto dto) {
        StudentDto studentDto = studentService.createStudent(dto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @PatchMapping("students/{studentId}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long studentId, @RequestBody Map<String, Object> updates) {
        StudentDto studentDto = studentService.updatePartialStudent(studentId, updates);
        return ResponseEntity.ok(studentDto);
    }


}
