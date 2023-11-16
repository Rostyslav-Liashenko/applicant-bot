package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.CostEducation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CostEducationRepository extends CrudRepository<CostEducation, UUID> { }
