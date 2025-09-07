package com.practice.services.impl;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;
import com.practice.entities.Enrollment;
import com.practice.entities.Grade;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.EnrollmentRepository;
import com.practice.repositories.GradeRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {


    private final GradeRepository gradeRepository;

    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public GradeDto addGradeByStudentId(Long studentId, CreateGradeDto dto) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId));
        }

        Enrollment enrollment = enrollmentRepository.findByStudentStudentIdAndIsActiveTrue(studentId);
        if (gradeRepository.existsByEnrollmentEnrollmentIdAndCourseSemesterNumber(enrollment.getEnrollmentId(), dto.getCourseSemesterNumber())) {
            throw new IllegalArgumentException("Grades stored Already");
        }
        Grade grade = new Grade();
        grade.setCourseSemesterNumber(dto.getCourseSemesterNumber());
        grade.setAppearedSemesterName(dto.getAppearedSemesterName());
        grade.setPercentage(dto.getPercentage());
        grade.setResultStatus(dto.getResultStatus());
        grade.setEnrollment(enrollment);

        Grade savedGrade = gradeRepository.save(grade);

        return modelMapper.map(savedGrade, GradeDto.class);
    }

    @Override
    public List<GradeDto> getGradesByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId));
        }
        List<Grade> grades = gradeRepository.findByEnrollmentStudentStudentId(studentId);
        List<GradeDto> gradeDtoList = grades.stream()
                .map((grade) -> modelMapper.map(grade, GradeDto.class)).collect(Collectors.toList());
        return gradeDtoList;
    }

    @Override
    public List<GradeDto> getActiveCourseGradesByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId));
        }
        List<Grade> grades = gradeRepository.findByEnrollmentStudentStudentIdAndEnrollmentIsActiveTrue(studentId);
        List<GradeDto> gradeDtoList = grades.stream()
                .map((grade) -> modelMapper.map(grade, GradeDto.class)).collect(Collectors.toList());

        return gradeDtoList;
    }

    @Override
    public List<GradeDto> getActiveCourseGradesByStudentIdAndCourseSemesterNumber(Long studentId, Long courseSemesterNumber) {
        List<Grade> grades = gradeRepository
                .findByEnrollmentStudentStudentIdAndCourseSemesterNumberAndEnrollmentIsActiveTrue(studentId, courseSemesterNumber);
        List<GradeDto> gradeDtoList = grades.stream()
                .map((grade) -> modelMapper.map(grade, GradeDto.class)).collect(Collectors.toList());

        return gradeDtoList;
    }


    @Override
    public GradeDto updatePartialGradeByGradeId(Long gradeId, Map<String, Object> updates) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Grade", "gradeId", String.valueOf(gradeId)));

        updates.forEach((field, value) -> {
            switch (field) {
                case "courseSemesterNumber":
                    Integer courseSemesterNumber = ((Number) value).intValue();
                    grade.setCourseSemesterNumber(courseSemesterNumber);
                    break;
                case "appearedSemesterName":
                    grade.setAppearedSemesterName((String) value);
                    break;
                case "percentage":
                    Double percentage = ((Number) value).doubleValue();
                    grade.setPercentage(percentage);
                    break;
                case "resultStatus":
                    grade.setResultStatus((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });
        Grade savedGrade = gradeRepository.save(grade);

        return modelMapper.map(savedGrade, GradeDto.class);
    }
}
