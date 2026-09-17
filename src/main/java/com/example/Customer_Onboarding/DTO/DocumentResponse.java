package com.example.Customer_Onboarding.DTO;

import com.example.Customer_Onboarding.Entity.DocumentStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DocumentResponse {

    private UUID id;
    private UUID customerId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private DocumentStatus status;
    private LocalDateTime uploadDate;

    public DocumentResponse(UUID id, UUID customerId, String fileName, String fileType,
                            Long fileSize, DocumentStatus status, LocalDateTime uploadDate) {
        this.id = id;
        this.customerId = customerId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.status = status;
        this.uploadDate = uploadDate;
    }
}