package com.practice.services.impl;

import com.practice.dto.EnrollmentDto;
import com.practice.entities.Course;
import com.practice.entities.Enrollment;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CourseRepository;
import com.practice.repositories.EnrollmentRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.EnrollmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;


    @Transactional
    @Override
    public EnrollmentDto createEnrollment(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", String.valueOf(courseId)));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId)));

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return modelMapper.map(savedEnrollment, EnrollmentDto.class);
    }
}
