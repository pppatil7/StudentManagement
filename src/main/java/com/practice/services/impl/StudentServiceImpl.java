package com.practice.services.impl;

import com.practice.dto.CreateStudentDto;
import com.practice.dto.EnrollmentDto;
import com.practice.dto.StudentDto;
import com.practice.entities.Enrollment;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CourseRepository;
import com.practice.repositories.EnrollmentRepository;
import com.practice.repositories.StudentRepository;
import com.practice.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;


    @Override
    public StudentDto createStudent(CreateStudentDto dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long studentId, Map<String, Object> updates) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId)));

        updates.forEach((field, value) -> {
            switch (field) {
                case "studentFirstName":
                    student.setStudentFirstName((String) value);
                    break;
                case "studentLastName":
                    student.setStudentLastName((String) value);
                    break;
                case "studentEmail":
                    student.setStudentEmail((String) value);
                    break;
                case "studentAddress":
                    student.setStudentAddress((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto getStudentByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId)));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
        return studentDtoList;
    }

    @Override
    public List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student", "studentId", String.valueOf(studentId));
        }
        List<Enrollment> enrollments = enrollmentRepository.findByStudentStudentId(studentId);

        List<EnrollmentDto> enrollmentDtoList = enrollments.stream()
                .map((enrollment) -> modelMapper.map(enrollment, EnrollmentDto.class)).collect(Collectors.toList());

        return enrollmentDtoList;
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
}
