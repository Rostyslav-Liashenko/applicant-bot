package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "cost_education")
public class CostEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String pathToFile;
    private String description;

    private Date timeRelevance;
}
