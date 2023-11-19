package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.AdmissionRule;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdmissionRuleRepository extends CrudRepository<AdmissionRule, UUID> { }
