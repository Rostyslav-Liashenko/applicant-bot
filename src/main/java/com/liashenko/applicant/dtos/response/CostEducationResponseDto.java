package com.liashenko.applicant.dtos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CostEducationResponseDto extends DocumentResponseDto {
    private Date timeRelevance;
}
