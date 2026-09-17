package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.ActivityPriority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ActivityRequest {

    // activity name should be enum
    // Verify Documents
    // Validate Store Details
    // Approve Customer Profile
    // Activate Account
    @NotBlank(message = "Activity name is required")
    private String activityName;

    @NotBlank(message = "Assigned to is required")
    private String assignedTo;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    @NotNull(message = "Priority is required")
    private ActivityPriority priority;
}