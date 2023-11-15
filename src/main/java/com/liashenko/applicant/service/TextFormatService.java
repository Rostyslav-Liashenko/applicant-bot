package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextFormatService {
    public String educationProgramDtosToText(List<EducationProgramResponseDto> educationProgramResponseDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        for (EducationProgramResponseDto educationProgram : educationProgramResponseDtos) {
            stringBuilder.append(educationProgram.getName()).append("\n");
        }

        return stringBuilder.toString();
    }

    public String documentAdmissionDtosToText(List<DocumentAdmissionResponseDto> documentAdmissionResponseDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        for (DocumentAdmissionResponseDto documentAdmission : documentAdmissionResponseDtos) {
            stringBuilder.append(documentAdmission.getDescription()).append("\n");
        }

        return stringBuilder.toString();
    }
}
