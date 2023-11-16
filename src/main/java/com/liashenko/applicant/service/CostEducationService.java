package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.CostEducationResponseDto;
import com.liashenko.applicant.entity.CostEducation;
import com.liashenko.applicant.repository.CostEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CostEducationService {
    private final CostEducationRepository costEducationRepository;

    @Autowired
    public CostEducationService(CostEducationRepository costEducationRepository) {
        this.costEducationRepository = costEducationRepository;
    }

    public List<CostEducationResponseDto> getAll() {
        Iterable<CostEducation> costEducations = this.costEducationRepository.findAll();
        Stream<CostEducation> stream = StreamSupport.stream(costEducations.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public CostEducationResponseDto toDto(CostEducation costEducation) {
        CostEducationResponseDto costEducationResponseDto = new CostEducationResponseDto();

        costEducationResponseDto.setPathToFile(costEducation.getPathToFile());
        costEducationResponseDto.setTimeRelevance(costEducation.getTimeRelevance());
        costEducationResponseDto.setDescription(costEducation.getDescription());

        return  costEducationResponseDto;
    }
}
