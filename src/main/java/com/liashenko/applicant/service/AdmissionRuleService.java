package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.AdmissionRuleResponseDto;
import com.liashenko.applicant.entity.AdmissionRule;
import com.liashenko.applicant.repository.AdmissionRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AdmissionRuleService {
    private final AdmissionRuleRepository admissionRuleRepository;

    @Autowired
    public AdmissionRuleService(AdmissionRuleRepository admissionRuleRepository) {
        this.admissionRuleRepository = admissionRuleRepository;
    }

    public List<AdmissionRuleResponseDto> getAll() {
        Iterable<AdmissionRule> admissionRules = this.admissionRuleRepository.findAll();
        Stream<AdmissionRule> stream = StreamSupport.stream(admissionRules.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public AdmissionRuleResponseDto toDto(AdmissionRule admissionRule) {
        AdmissionRuleResponseDto admissionRuleResponseDto = new AdmissionRuleResponseDto();

        admissionRuleResponseDto.setPathToFile(admissionRule.getPathToFile());
        admissionRuleResponseDto.setDescription(admissionRule.getDescription());

        return  admissionRuleResponseDto;
    }
}
