package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateStudentDto {

    private String studentFirstName;

    private String studentLastName;

    private String studentEmail;

    private String studentAddress;
}
