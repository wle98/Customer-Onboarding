package com.example.Customer_Onboarding.Controller;

import com.example.Customer_Onboarding.DTO.DocumentResponse;
import com.example.Customer_Onboarding.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/api/customers/{customerId}/documents", consumes = "multipart/form-data")
    public ResponseEntity<DocumentResponse> uploadDocument(
            @PathVariable UUID customerId, @RequestParam("file") MultipartFile file) {
        DocumentResponse uploaded = documentService.uploadDocument(customerId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploaded);
    }

    @GetMapping("/api/customers/{customerId}/documents")
    public ResponseEntity<List<DocumentResponse>> getDocumentsByCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(documentService.getDocumentsByCustomer(customerId));
    }

    @GetMapping("/api/documents/{id}")
    public ResponseEntity<DocumentResponse> getDocument(@PathVariable UUID id) {
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    @DeleteMapping("/api/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}