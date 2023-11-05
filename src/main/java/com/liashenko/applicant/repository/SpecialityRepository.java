package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> { }
