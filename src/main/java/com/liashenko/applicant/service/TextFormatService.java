package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.dtos.response.OpenDayResponseDto;
import com.liashenko.applicant.dtos.response.SpecialityResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    public String OpenDayDtosToText(List<OpenDayResponseDto> openDayResponseDtos) {
        StringBuilder stringBuilder = new StringBuilder();

        for (OpenDayResponseDto openDayResponseDto: openDayResponseDtos) {
            String formattedDate = this.FormattedDate(openDayResponseDto.getDate());

            stringBuilder
                    .append(formattedDate)
                    .append(" ")
                    .append(openDayResponseDto.getDescription())
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public String FormattedDate(Date date) {
        LocalDate localDate = date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int day = localDate.getDayOfMonth();
        int month = localDate.getMonth().getValue();
        int year = localDate.getYear();

        return day + "-" + month + "-" + year;
    }
}
