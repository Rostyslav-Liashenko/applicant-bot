package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.BranchOfKnowledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchOfKnowledgeRepository extends CrudRepository<BranchOfKnowledge, UUID> { }
