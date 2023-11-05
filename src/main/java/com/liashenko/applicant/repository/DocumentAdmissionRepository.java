package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.DocumentAdmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentAdmissionRepository extends JpaRepository<DocumentAdmission, UUID> { }
