package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.OnboardingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter

@Getter
public class StatusUpdateRequest {

    @NotNull(message = "New status is required")
    private OnboardingStatus newStatus;

    @NotNull(message = "changedBy is required")
    private String changedBy;
}