package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.EducationProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, UUID> { }
