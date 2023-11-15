package com.liashenko.applicant.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class SpecialityResponseDto {
    private int code;
    private String name;
    private List<EducationProgramResponseDto>  educationProgramResponseDtos;
}
