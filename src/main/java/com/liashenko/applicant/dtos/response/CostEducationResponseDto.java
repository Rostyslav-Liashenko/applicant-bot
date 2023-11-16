package com.liashenko.applicant.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class CostEducationResponseDto {
    private String pathToFile;
    private String description;
    private Date timeRelevance;
}
