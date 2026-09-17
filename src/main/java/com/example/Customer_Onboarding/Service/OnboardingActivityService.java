package com.example.Customer_Onboarding.Service;

import com.example.Customer_Onboarding.DTO.ActivityRequest;
import com.example.Customer_Onboarding.DTO.ActivityResponse;
import com.example.Customer_Onboarding.Entity.ActivityStatus;
import com.example.Customer_Onboarding.Entity.Customer;
import com.example.Customer_Onboarding.Entity.OnboardingActivity;
import com.example.Customer_Onboarding.Exception.ResourceNotFoundException;
import com.example.Customer_Onboarding.Repository.OnboardingActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OnboardingActivityService {

    @Autowired
    private OnboardingActivityRepository activityRepository;

    @Autowired
    private CustomerService customerService;

    public ActivityResponse createActivity(UUID customerId, ActivityRequest request) {
        Customer customer = customerService.getCustomerById(customerId);

        OnboardingActivity activity = new OnboardingActivity();
        activity.setCustomer(customer);
        activity.setActivityName(request.getActivityName());
        activity.setAssignedTo(request.getAssignedTo());
        activity.setDueDate(request.getDueDate());
        activity.setPriority(request.getPriority());

        return toResponse(activityRepository.save(activity));
    }

    public List<ActivityResponse> getActivitiesByCustomer(UUID customerId) {
        customerService.getCustomerById(customerId);
        return activityRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ActivityResponse getActivityById(UUID id) {
        return toResponse(findActivityOrThrow(id));
    }

    public ActivityResponse updateActivity(UUID id, ActivityRequest request) {
        OnboardingActivity activity = findActivityOrThrow(id);
        activity.setActivityName(request.getActivityName());
        activity.setAssignedTo(request.getAssignedTo());
        activity.setDueDate(request.getDueDate());
        activity.setPriority(request.getPriority());
        return toResponse(activityRepository.save(activity));
    }

    public ActivityResponse updateActivityStatus(UUID id, ActivityStatus newStatus) {
        OnboardingActivity activity = findActivityOrThrow(id);
        activity.setStatus(newStatus);
        return toResponse(activityRepository.save(activity));
    }

    public void deleteActivity(UUID id) {
        OnboardingActivity activity = findActivityOrThrow(id);
        activityRepository.delete(activity);
    }

    private OnboardingActivity findActivityOrThrow(UUID id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
    }

    private ActivityResponse toResponse(OnboardingActivity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getCustomer().getId(),
                activity.getActivityName(),
                activity.getAssignedTo(),
                activity.getDueDate(),
                activity.getPriority(),
                activity.getStatus(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }
}