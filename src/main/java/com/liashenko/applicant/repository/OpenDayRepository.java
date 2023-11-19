package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.OpenDay;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OpenDayRepository extends CrudRepository<OpenDay, UUID> { }
