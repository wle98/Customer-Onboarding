package com.example.Customer_Onboarding.Service;

import com.example.Customer_Onboarding.DTO.DocumentResponse;
import com.example.Customer_Onboarding.Entity.Customer;
import com.example.Customer_Onboarding.Entity.Document;
import com.example.Customer_Onboarding.Entity.DocumentStatus;
import com.example.Customer_Onboarding.Exception.InvalidFileException;
import com.example.Customer_Onboarding.Exception.ResourceNotFoundException;
import com.example.Customer_Onboarding.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DocumentService {

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "application/pdf", "image/jpeg", "image/png"
    );
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private NotificationService notificationService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public DocumentResponse uploadDocument(UUID customerId, MultipartFile file) {
        Customer customer = customerService.getCustomerById(customerId);

        validateFile(file);

        String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(uploadDir).resolve(storedFileName);

        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new InvalidFileException("Failed to store file: " + e.getMessage());
        }

        Document document = new Document();
        document.setCustomer(customer);
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFilePath(targetPath.toString());
        document.setFileSize(file.getSize());

        document.setStatus(DocumentStatus.PENDING);
        Document saved = documentRepository.save(document);
        notificationService.notifyDocumentPendingUpload(customer, saved.getFileName());
        return toResponse(saved);
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidFileException("File is empty");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidFileException("File exceeds maximum size of 10MB");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new InvalidFileException("Unsupported file format. Allowed: PDF, JPG, PNG");
        }
    }

    public List<DocumentResponse> getDocumentsByCustomer(UUID customerId) {
        customerService.getCustomerById(customerId);
        return documentRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DocumentResponse getDocumentById(UUID id) {
        return toResponse(findDocumentOrThrow(id));
    }

    public void deleteDocument(UUID id) {
        Document document = findDocumentOrThrow(id);
        try {
            Files.deleteIfExists(Paths.get(document.getFilePath()));
        } catch (IOException e) {
            // log and continue. don't block DB cleanup over a missing/locked file
        }
        documentRepository.delete(document);
    }

    private Document findDocumentOrThrow(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
    }

    private DocumentResponse toResponse(Document document) {
        return new DocumentResponse(
                document.getId(),
                document.getCustomer().getId(),
                document.getFileName(),
                document.getFileType(),
                document.getFileSize(),
                document.getStatus(),
                document.getUploadDate()
        );
    }
}