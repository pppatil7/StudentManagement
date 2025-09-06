package com.practice.services.impl;

import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.dto.StudentDto;
import com.practice.entities.Course;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CourseRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Override
    public CourseDto createCourse(CreateCourseDto dto) {
        Course course = modelMapper.map(dto, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public List<StudentDto> getAllStudentsByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "courseId", String.valueOf(courseId));
        }
        List<Student> students = studentRepository.findByEnrollmentsCourseCourseId(courseId);
        List<StudentDto> studentDtoList = students.stream()
                .map((student) -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());

        return studentDtoList;
    }

    @Override
    public List<StudentDto> getActiveStudentsByCourseId(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "courseId", String.valueOf(courseId));
        }
        List<Student> students = studentRepository.findByEnrollmentsCourseCourseIdAndEnrollmentsIsActiveTrue(courseId);
        List<StudentDto> studentDtoList = students.stream()
                .map((student) -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());

        return studentDtoList;
    }

}
