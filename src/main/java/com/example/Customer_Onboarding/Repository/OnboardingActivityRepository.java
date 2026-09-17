package com.example.Customer_Onboarding.Repository;

import com.example.Customer_Onboarding.Entity.OnboardingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OnboardingActivityRepository extends JpaRepository<OnboardingActivity, UUID> {
    List<OnboardingActivity> findByCustomerId(UUID customerId);
}