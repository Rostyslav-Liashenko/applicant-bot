package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.DocumentAdmission;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DocumentAdmissionRepository extends CrudRepository<DocumentAdmission, UUID> { }
