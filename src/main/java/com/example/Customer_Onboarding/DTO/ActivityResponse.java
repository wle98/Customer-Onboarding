package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.ActivityPriority;
import com.example.Customer_Onboarding.Entity.ActivityStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ActivityResponse {

    private UUID id;
    private UUID customerId;
    private String activityName;
    private String assignedTo;
    private LocalDate dueDate;
    private ActivityPriority priority;
    private ActivityStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ActivityResponse(UUID id, UUID customerId, String activityName, String assignedTo,
                            LocalDate dueDate, ActivityPriority priority, ActivityStatus status,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.activityName = activityName;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}