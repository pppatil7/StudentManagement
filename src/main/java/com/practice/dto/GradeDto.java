package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeDto {

    private Long gradeId;

    private CourseDto course;

    private StudentDto student;

    private Integer courseSemesterNumber;

    private String appearedSemesterName;

    private Double percentage;

    private String resultStatus;


}
