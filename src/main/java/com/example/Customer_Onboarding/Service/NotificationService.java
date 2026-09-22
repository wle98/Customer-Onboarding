package com.example.Customer_Onboarding.Service;

import com.example.Customer_Onboarding.DTO.NotificationResponse;
import com.example.Customer_Onboarding.Entity.*;
import com.example.Customer_Onboarding.Repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    // One entry point every trigger funnels through.
    // Swapping this to actually send email later only touches this method.
    private void send(Customer customer, NotificationTriggerEvent event, String message) {
        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setTriggerEvent(event);
        notification.setMessage(message);
        notificationRepository.save(notification);

        // Stand-in for real email sending until SMTP is wired up.
        logger.info("NOTIFICATION [{}] to {}: {}", event, customer.getEmail(), message);
    }

    public void notifyDocumentPendingUpload(Customer customer, String fileName) {
        send(customer, NotificationTriggerEvent.DOCUMENT_PENDING_UPLOAD,
                "Document \"" + fileName + "\" was uploaded and is pending review.");
    }

    public void notifyActivityAssigned(Customer customer, String activityName, String assignedTo) {
        send(customer, NotificationTriggerEvent.ACTIVITY_ASSIGNED,
                "Activity \"" + activityName + "\" was assigned to " + assignedTo + ".");
    }

    public void notifyActivityOverdue(Customer customer, String activityName) {
        send(customer, NotificationTriggerEvent.ACTIVITY_OVERDUE,
                "Activity \"" + activityName + "\" is overdue.");
    }

    public void notifyStatusChanged(Customer customer, OnboardingStatus oldStatus, OnboardingStatus newStatus) {
        send(customer, NotificationTriggerEvent.STATUS_CHANGED,
                "Onboarding status changed from " + oldStatus + " to " + newStatus + ".");
    }

    public void notifyOnboardingCompleted(Customer customer) {
        send(customer, NotificationTriggerEvent.ONBOARDING_COMPLETED,
                "Onboarding is complete for " + customer.getName() + ".");
    }

    public List<NotificationResponse> getNotificationsByCustomer(UUID customerId) {
        return notificationRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private NotificationResponse toResponse(Notification n) {
        return new NotificationResponse(
                n.getId(), n.getCustomer().getId(), n.getTriggerEvent(),
                n.getChannel(), n.getMessage(), n.getCreatedAt()
        );
    }
}