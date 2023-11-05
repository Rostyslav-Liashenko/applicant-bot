package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FacultyRepository extends CrudRepository<Faculty, UUID> { }
