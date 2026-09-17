package com.example.Customer_Onboarding.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "onboarding_activities")
public class OnboardingActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank(message = "Activity name is required")
    private String activityName;

    @NotBlank(message = "Assigned to is required")
    private String assignedTo;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private ActivityPriority priority;

    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = ActivityStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}