package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "admission_rules")
public class AdmissionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String pathToFile;
    private String description;
}
