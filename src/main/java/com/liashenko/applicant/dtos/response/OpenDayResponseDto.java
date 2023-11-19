package com.liashenko.applicant.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class OpenDayResponseDto {
    private Date date;
    private String description;
}
