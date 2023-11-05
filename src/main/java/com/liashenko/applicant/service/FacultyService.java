package com.liashenko.applicant.service;

import com.liashenko.applicant.entity.Faculty;
import com.liashenko.applicant.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Iterable<Faculty> getFaculties() {
        return this.facultyRepository.findAll();
    }
}
