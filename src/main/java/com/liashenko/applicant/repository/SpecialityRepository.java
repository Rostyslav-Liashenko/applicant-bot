package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpecialityRepository extends CrudRepository<Speciality, UUID> {
    @Query("SELECT s FROM Speciality s INNER JOIN FETCH s.educationPrograms")
    Iterable<Speciality> findSpecialityWithEducationProgram();
}
