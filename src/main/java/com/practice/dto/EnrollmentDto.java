package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentDto {

    private Long enrollmentId;

    private LocalDate enrollmentDate;

    private StudentDto student;

    private CourseDto course;
}
