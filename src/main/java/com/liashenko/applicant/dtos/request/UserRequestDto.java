package com.liashenko.applicant.dtos.request;

import lombok.Data;

@Data
public class UserRequestDto {
    public String chatId;
    public String firstName;
    public String lastName;
    public boolean isShowNotification;
}
