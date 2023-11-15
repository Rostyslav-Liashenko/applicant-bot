package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.EducationProgramResponseDto;
import com.liashenko.applicant.dtos.response.SpecialityResponseDto;
import com.liashenko.applicant.entity.Speciality;
import com.liashenko.applicant.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class SpecialityService {

    private final EducationProgramService educationProgramService;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(EducationProgramService educationProgramService, SpecialityRepository specialityRepository) {
        this.educationProgramService = educationProgramService;
        this.specialityRepository = specialityRepository;
    }

    public List<SpecialityResponseDto> getAll() {
        Iterable<Speciality> specialities = this.specialityRepository.findSpecialityWithEducationProgram();
        Stream<Speciality> stream = StreamSupport.stream(specialities.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public SpecialityResponseDto toDto(Speciality speciality) {
        SpecialityResponseDto specialityResponseDto = new SpecialityResponseDto();

        List<EducationProgramResponseDto> educationProgramResponseDtos = speciality
                .getEducationPrograms()
                .stream()
                .map(this.educationProgramService::toDto)
                .toList();

        specialityResponseDto.setCode(speciality.getCode());
        specialityResponseDto.setName(speciality.getName());
        specialityResponseDto.setEducationProgramResponseDtos(educationProgramResponseDtos);

        return specialityResponseDto;
    }
}
