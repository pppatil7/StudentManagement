package com.practice.services.impl;

import com.practice.dto.ApiResponse;
import com.practice.dto.CreateEnrollmentDto;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;


    @Transactional
    @Override
    public EnrollmentDto createEnrollment(CreateEnrollmentDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", String.valueOf(dto.getCourseId())));
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(dto.getStudentId())));

        if (enrollmentRepository.existsByCourseCourseIdAndStudentStudentId(dto.getCourseId(), dto.getStudentId())) {
            throw new IllegalArgumentException("Course Already Assigned to Student");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(LocalDate.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return modelMapper.map(savedEnrollment, EnrollmentDto.class);
    }

    @Transactional
    @Override
    public ApiResponse updatePartialEnrollmentByEnrollmentId(Long enrollmentId, Map<String, Object> updates) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "enrollmentId", String.valueOf(enrollmentId)));

        CreateEnrollmentDto dto = modelMapper.map(updates, CreateEnrollmentDto.class);

        if (enrollmentRepository.existsByCourseCourseIdAndStudentStudentId(dto.getCourseId(), dto.getStudentId())) {
            throw new IllegalArgumentException("Course Already Assigned to Student");
        }

        updates.forEach((field, value) -> {
            switch (field) {
                case "courseId":
                    Long courseId = ((Number) value).longValue();
                    enrollment.setCourse(courseRepository.findById(courseId).orElseThrow());
                    break;
                case "studentId":
                    Long studentId = ((Number) value).longValue();
                    enrollment.setStudent(studentRepository.findById(studentId).orElseThrow());
                    break;
                default:
                    throw new IllegalArgumentException("Field not Supported");
            }
        });
        return new ApiResponse("Enrollment Updated Successfully", true);
    }
}
