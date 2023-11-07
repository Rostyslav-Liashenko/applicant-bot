package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "specialties")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private int code;

    @ManyToOne()
    @JoinColumn(name = "branch_of_knowledges_id", nullable = false)
    private BranchOfKnowledge branchOfKnowledge;

    @OneToMany(mappedBy = "speciality")
    private List<EducationProgram> educationPrograms = new ArrayList<>();
}
