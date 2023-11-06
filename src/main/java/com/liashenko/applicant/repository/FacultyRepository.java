package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FacultyRepository extends CrudRepository<Faculty, UUID> {
    @Query("SELECT f FROM Faculty f INNER JOIN FETCH f.branchOfKnowledges")
    Iterable<Faculty> findFacultiesWithBranchOfKnowledge();
}
