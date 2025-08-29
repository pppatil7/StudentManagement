package com.practice.services.impl;

import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.entities.Course;
import com.practice.repositories.CourseRepository;
import com.practice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Override
    public CourseDto createCourse(CreateCourseDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDto.class);
    }





}
