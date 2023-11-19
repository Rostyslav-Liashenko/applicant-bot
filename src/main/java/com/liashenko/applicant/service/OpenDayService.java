package com.liashenko.applicant.service;

import com.liashenko.applicant.repository.OpenDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenDayService {
    private final OpenDayRepository openDayRepository;

    @Autowired
    public OpenDayService(OpenDayRepository openDayRepository) {
        this.openDayRepository = openDayRepository;
    }
}
