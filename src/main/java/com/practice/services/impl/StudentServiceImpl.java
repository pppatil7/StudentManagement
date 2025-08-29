package com.practice.services.impl;

import com.practice.dto.CreateStudentDto;
import com.practice.dto.StudentDto;
import com.practice.entities.Student;
import com.practice.repositories.StudentRepository;
import com.practice.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Override
    public StudentDto createStudent(CreateStudentDto dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long studentId, Map<String, Object> updates) {
        Student student = studentRepository.findById(studentId).orElseThrow();

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
}
