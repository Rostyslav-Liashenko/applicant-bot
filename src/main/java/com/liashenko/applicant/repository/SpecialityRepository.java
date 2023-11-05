package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Speciality;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpecialityRepository extends CrudRepository<Speciality, UUID> { }
