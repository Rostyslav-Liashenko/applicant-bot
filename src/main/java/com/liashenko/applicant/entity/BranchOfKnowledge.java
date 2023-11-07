package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "branch_of_knowledges")
public class BranchOfKnowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private int code;

    @ManyToOne()
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(mappedBy = "branchOfKnowledge", fetch = FetchType.EAGER)
    private List<Speciality> specialities = new ArrayList<>();
}
