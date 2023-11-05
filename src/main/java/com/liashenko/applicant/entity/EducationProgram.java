package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "education_programs")
public class EducationProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
}
