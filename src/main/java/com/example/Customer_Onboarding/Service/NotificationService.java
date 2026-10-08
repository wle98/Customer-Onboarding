package com.example.Customer_Onboarding.Service;

import com.example.Customer_Onboarding.DTO.NotificationResponse;
import com.example.Customer_Onboarding.Entity.*;
import com.example.Customer_Onboarding.Repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    // One entry point every trigger funnels through.
    // Swapping this to actually send email later only touches this method.
    private void send(Customer customer, NotificationTriggerEvent event, String message) {
        Notification notification = new Notification();
        notification.setCustomer(customer);
        notification.setTriggerEvent(event);
        notification.setMessage(message);
        notificationRepository.save(notification);

        logger.info("NOTIFICATION [{}] to {}: {}", event, customer.getEmail(), message);

        if (mailSender == null) {
            logger.info("Mail not configured, skipping email to {}", customer.getEmail());
            return;
        }

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(customer.getEmail());
            email.setSubject("Customer Onboarding Update");
            email.setText(message);
            mailSender.send(email);
        } catch (MailException e) {
            // Don't let a failed/slow email roll back the actual business action
            // (document upload, status change, etc.) that triggered this notification.
            logger.warn("Failed to send notification email to {}: {}", customer.getEmail(), e.getMessage());
        }
    }

    public void notifyDocumentPendingUpload(Customer customer, String fileName) {
        send(customer, NotificationTriggerEvent.DOCUMENT_PENDING_UPLOAD,
                "Document \"" + fileName + "\" was uploaded and is pending review.");
    }

    public void notifyDocumentReviewed(Customer customer, String fileName, DocumentStatus status) {
        send(customer, NotificationTriggerEvent.DOCUMENT_REVIEWED,
                "Document \"" + fileName + "\" review complete: marked " + status + ".");
    }

    public void notifyActivityAssigned(Customer customer, String activityName, String assignedTo,
                                       LocalDate dueDate, ActivityPriority priority) {
        send(customer, NotificationTriggerEvent.ACTIVITY_ASSIGNED,
                "Activity \"" + activityName + "\" was assigned to " + assignedTo
                        + ". Due date: " + dueDate + ". Priority: " + priority + ".");
    }

    public void notifyActivityOverdue(Customer customer, String activityName,
                                      LocalDate dueDate, ActivityPriority priority) {
        send(customer, NotificationTriggerEvent.ACTIVITY_OVERDUE,
                "Activity \"" + activityName + "\" is overdue. Due date: " + dueDate
                        + ". Priority: " + priority + ".");
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