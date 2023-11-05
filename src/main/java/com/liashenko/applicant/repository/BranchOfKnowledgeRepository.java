package com.liashenko.applicant.repository;

import com.liashenko.applicant.entity.BranchOfKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchOfKnowledgeRepository extends JpaRepository<BranchOfKnowledge, UUID> { }
