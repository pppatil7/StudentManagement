package com.practice.controllers;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;
import com.practice.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class GradeController {

    private final GradeService gradeService;


    @PostMapping("courses/students/grades")
    public ResponseEntity<GradeDto> createGrade(@RequestBody CreateGradeDto dto) {
        GradeDto gradeDto = gradeService.createGrade(dto);
        return ResponseEntity.ok(gradeDto);
    }


}
