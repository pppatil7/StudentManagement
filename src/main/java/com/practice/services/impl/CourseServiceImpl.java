package com.practice.services.impl;

import com.practice.dto.ApiResponse;
import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.entities.Course;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CourseRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

//    @Transactional
//    @Override
//    public ApiResponse assignCourseToStudent(Long courseId, Long studentId) {
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", String.valueOf(courseId)));
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId)));
//        boolean isAlreadyAssigned = courseRepository.existsByCourseIdAndStudentsStudentId(courseId, studentId);
//        if (isAlreadyAssigned) {
//            throw new RuntimeException("Course Already assigned to Student");
//        }
//        student.getCourses().add(course);
//        course.getStudents().add(student); //bidirectional
//        return new ApiResponse("Course Assigned to Student Successfully", true);
//    }
}
