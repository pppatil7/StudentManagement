package com.practice.services.impl;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;
import com.practice.entities.Course;
import com.practice.entities.Grade;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CourseRepository;
import com.practice.repositories.GradeRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {


    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final GradeRepository gradeRepository;

    private final ModelMapper modelMapper;


    @Override
    public GradeDto createGrade(CreateGradeDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(dto.getStudentId())));
        Course course = courseRepository.findByEnrollmentsStudentStudentIdAndEnrollmentsIsActiveTrue(dto.getStudentId());

        if (course == null) {
            throw new IllegalArgumentException("Student not actively enrolled to any course");
        }


        Grade grade = new Grade();
        grade.setCourse(course);
        grade.setStudent(student);
        grade.setCourseSemesterNumber(dto.getCourseSemesterNumber());
        grade.setAppearedSemesterName(dto.getAppearedSemesterName());
        grade.setPercentage(dto.getPercentage());
        grade.setResultStatus(dto.getResultStatus());

        Grade savedGrade = gradeRepository.save(grade);

        return modelMapper.map(savedGrade, GradeDto.class);
    }


}
