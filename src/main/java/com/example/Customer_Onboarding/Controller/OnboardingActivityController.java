package com.example.Customer_Onboarding.Controller;

import com.example.Customer_Onboarding.DTO.ActivityRequest;
import com.example.Customer_Onboarding.DTO.ActivityResponse;
import com.example.Customer_Onboarding.Entity.ActivityStatus;
import com.example.Customer_Onboarding.Entity.OnboardingActivity;
import com.example.Customer_Onboarding.Service.OnboardingActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OnboardingActivityController {

    @Autowired
    private OnboardingActivityService activityService;

    @PostMapping("/api/customers/{customerId}/activities")
    public ResponseEntity<ActivityResponse> createActivity(
            @PathVariable UUID customerId, @Valid @RequestBody ActivityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.createActivity(customerId, request));
    }

    @GetMapping("/api/customers/{customerId}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(activityService.getActivitiesByCustomer(customerId));
    }

    @GetMapping("/api/activities/{id}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable UUID id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }

    @PutMapping("/api/activities/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(
            @PathVariable UUID id, @Valid @RequestBody ActivityRequest request) {
        return ResponseEntity.ok(activityService.updateActivity(id, request));
    }

    @PatchMapping("/api/activities/{id}/status")
    public ResponseEntity<ActivityResponse> updateStatus(
            @PathVariable UUID id, @RequestParam ActivityStatus status) {
        return ResponseEntity.ok(activityService.updateActivityStatus(id, status));
    }

    @DeleteMapping("/api/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}