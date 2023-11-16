package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.dtos.response.SpecialityResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextFormatService {
    public String educationProgramDtosToText(
            List<EducationProgramResponseDto> educationProgramResponseDtos,
            int countShifts
    ) {
        StringBuilder stringBuilder = new StringBuilder();

        for (EducationProgramResponseDto educationProgram : educationProgramResponseDtos) {
            String shifts = "-".repeat(countShifts);
            stringBuilder
                    .append(shifts)
                    .append(educationProgram.getName())
                    .append("\n");
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

    public String specialityDtosToText(List<SpecialityResponseDto> specialityResponseDtos) {
        StringBuilder stringBuilder = new StringBuilder();
        int countShiftText = 2;

        for (SpecialityResponseDto specialityResponseDto: specialityResponseDtos) {
            String educationText = this.educationProgramDtosToText(
                    specialityResponseDto.getEducationProgramResponseDtos(),
                    countShiftText
            );

            stringBuilder
                    .append(specialityResponseDto.getCode())
                    .append(" ")
                    .append(specialityResponseDto.getName())
                    .append("\n")
                    .append(educationText);
        }

        return stringBuilder.toString();
    }
}
