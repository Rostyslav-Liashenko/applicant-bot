package com.liashenko.applicant.service;

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

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<SpecialityResponseDto> getAll() {
        Iterable<Speciality> specialities = this.specialityRepository.findAll();
        Stream<Speciality> stream = StreamSupport.stream(specialities.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public SpecialityResponseDto toDto(Speciality speciality) {
        SpecialityResponseDto specialityResponseDto = new SpecialityResponseDto();

        specialityResponseDto.setCode(speciality.getCode());
        specialityResponseDto.setName(speciality.getName());

        return specialityResponseDto;
    }
}
