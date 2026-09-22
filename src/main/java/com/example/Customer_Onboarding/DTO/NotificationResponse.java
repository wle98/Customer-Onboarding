package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.NotificationChannel;
import com.example.Customer_Onboarding.Entity.NotificationTriggerEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class NotificationResponse {

    private UUID id;
    private UUID customerId;
    private NotificationTriggerEvent triggerEvent;
    private NotificationChannel channel;
    private String message;
    private LocalDateTime createdAt;

    public NotificationResponse(UUID id, UUID customerId, NotificationTriggerEvent triggerEvent,
                                NotificationChannel channel, String message, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.triggerEvent = triggerEvent;
        this.channel = channel;
        this.message = message;
        this.createdAt = createdAt;
    }
}