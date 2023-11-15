package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.entity.EducationProgram;
import com.liashenko.applicant.repository.EducationProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class EducationProgramService {
    private final EducationProgramRepository educationProgramRepository;

    @Autowired
    public EducationProgramService(EducationProgramRepository educationProgramRepository) {
        this.educationProgramRepository = educationProgramRepository;
    }

    public List<EducationProgramResponseDto> getAll() {
        Iterable<EducationProgram> educationPrograms = this.educationProgramRepository.findAll();
        Stream<EducationProgram> stream = StreamSupport.stream(educationPrograms.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public EducationProgramResponseDto toDto(EducationProgram educationProgram) {
        EducationProgramResponseDto educationProgramResponseDto = new EducationProgramResponseDto();

        educationProgramResponseDto.setName(educationProgram.getName());

        return educationProgramResponseDto;
    }
}
