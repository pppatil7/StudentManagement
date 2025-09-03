package com.practice.controllers;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;
import com.practice.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("students/{studentId}/grades")
    public ResponseEntity<GradeDto> addGradeByStudentId(@PathVariable Long studentId, @RequestBody CreateGradeDto dto) {
        GradeDto gradeDto = gradeService.addGradeByStudentId(studentId, dto);
        return ResponseEntity.ok(gradeDto);
    }

    @GetMapping("students/{studentId}/grades")
    public ResponseEntity<List<GradeDto>> getGradesByStudentId(@PathVariable Long studentId) {
        List<GradeDto> gradeDtoList = gradeService.getGradesByStudentId(studentId);
        return ResponseEntity.ok(gradeDtoList);
    }


}
