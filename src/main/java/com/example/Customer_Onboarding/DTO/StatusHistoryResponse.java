package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.OnboardingStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class StatusHistoryResponse {

    private UUID id;
    private UUID customerId;
    private OnboardingStatus oldStatus;
    private OnboardingStatus newStatus;
    private String changedBy;
    private LocalDateTime changedAt;

    public StatusHistoryResponse(UUID id, UUID customerId, OnboardingStatus oldStatus,
                                 OnboardingStatus newStatus, String changedBy, LocalDateTime changedAt) {
        this.id = id;
        this.customerId = customerId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
    }
}