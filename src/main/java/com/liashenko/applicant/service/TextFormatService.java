package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextFormatService {
    public String EducationProgramDtosToText(List<EducationProgramResponseDto> educationProgramResponseDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        for (EducationProgramResponseDto educationProgram : educationProgramResponseDtos) {
            stringBuilder.append(educationProgram.getName()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String EducationProgramResponseDtoToText(EducationProgramResponseDto educationProgramResponseDto) {
        return educationProgramResponseDto.getName();
    }
}
