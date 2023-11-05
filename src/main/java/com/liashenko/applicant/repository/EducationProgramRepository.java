package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.EducationProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EducationProgramRepository extends CrudRepository<EducationProgram, UUID> { }
