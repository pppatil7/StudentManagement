package com.practice.services;

import com.practice.dto.ApiResponse;
import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;

public interface CourseService {

    CourseDto createCourse(CreateCourseDto dto);

//    ApiResponse assignCourseToStudent(Long courseId, Long studentId);
}
