package com.example.Customer_Onboarding.Scheduler;

import com.example.Customer_Onboarding.Entity.ActivityStatus;
import com.example.Customer_Onboarding.Entity.OnboardingActivity;
import com.example.Customer_Onboarding.Repository.OnboardingActivityRepository;
import com.example.Customer_Onboarding.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OverdueActivityScheduler {

    @Autowired
    private OnboardingActivityRepository activityRepository;

    @Autowired
    private NotificationService notificationService;

    // Runs once a day at midnight. Cron format: sec min hour day month weekday
    @Scheduled(cron = "0 0 0 * * *")
    public void flagOverdueActivities() {
        List<OnboardingActivity> activities = activityRepository.findAll();
        LocalDate today = LocalDate.now();

        for (OnboardingActivity activity : activities) {
            boolean isPastDue = activity.getDueDate().isBefore(today);
            boolean notAlreadyOverdue = activity.getStatus() != ActivityStatus.OVERDUE;
            boolean notAlreadyDone = activity.getStatus() != ActivityStatus.COMPLETED;

            if (isPastDue && notAlreadyOverdue && notAlreadyDone) {
                activity.setStatus(ActivityStatus.OVERDUE);
                activityRepository.save(activity);
                notificationService.notifyActivityOverdue(activity.getCustomer(), activity.getActivityName());
            }
        }
    }
}