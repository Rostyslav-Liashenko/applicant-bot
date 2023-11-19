package com.liashenko.applicant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_settings")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean isShowNotification;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
