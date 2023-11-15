package com.liashenko.applicant.service;

import com.liashenko.applicant.dtos.response.DocumentAdmissionResponseDto;
import com.liashenko.applicant.entity.DocumentAdmission;
import com.liashenko.applicant.repository.DocumentAdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DocumentAdmissionService {
    private final DocumentAdmissionRepository documentAdmissionRepository;

    @Autowired
    public DocumentAdmissionService(DocumentAdmissionRepository documentAdmissionRepository) {
        this.documentAdmissionRepository = documentAdmissionRepository;
    }

    public List<DocumentAdmissionResponseDto> getAll() {
        Iterable<DocumentAdmission> documentAdmissions = this.documentAdmissionRepository.findAll();
        Stream<DocumentAdmission> stream = StreamSupport.stream(documentAdmissions.spliterator(), false);

        return stream.map(this::toDto).collect(Collectors.toList());
    }

    public DocumentAdmissionResponseDto toDto(DocumentAdmission documentAdmission) {
        DocumentAdmissionResponseDto documentAdmissionResponseDto = new DocumentAdmissionResponseDto();

        documentAdmissionResponseDto.setDescription(documentAdmission.getDescription());

        return documentAdmissionResponseDto;
    }
}
