package com.example.Customer_Onboarding.Repository;

import com.example.Customer_Onboarding.Entity.OnboardingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OnboardingStatusHistoryRepository extends JpaRepository<OnboardingStatusHistory, UUID> {
    List<OnboardingStatusHistory> findByCustomerIdOrderByChangedAtDesc(UUID customerId);
}