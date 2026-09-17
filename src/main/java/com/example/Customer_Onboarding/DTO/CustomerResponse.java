package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.OnboardingStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String businessType;
    private LocalDate registrationDate;
    private OnboardingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerResponse(UUID id, String name, String email, String phone, String address,
                            String businessType, LocalDate registrationDate, OnboardingStatus status,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.businessType = businessType;
        this.registrationDate = registrationDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}