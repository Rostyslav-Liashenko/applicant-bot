package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> { }
