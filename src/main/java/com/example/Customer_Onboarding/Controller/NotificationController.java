package com.example.Customer_Onboarding.Controller;

import com.example.Customer_Onboarding.DTO.NotificationResponse;
import com.example.Customer_Onboarding.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/api/customers/notifications/{customerId}")
    public ResponseEntity<List<NotificationResponse>> getNotifications(@PathVariable UUID customerId) {
        return ResponseEntity.ok(notificationService.getNotificationsByCustomer(customerId));
    }
}