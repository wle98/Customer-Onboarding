package com.example.Customer_Onboarding.Controller;

import com.example.Customer_Onboarding.DTO.StatusHistoryResponse;
import com.example.Customer_Onboarding.DTO.StatusUpdateRequest;
import com.example.Customer_Onboarding.Entity.Customer;
import com.example.Customer_Onboarding.Entity.OnboardingStatusHistory;
import com.example.Customer_Onboarding.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // status

    @PatchMapping("/{id}/status")
    public ResponseEntity<Customer> updateStatus(@PathVariable UUID id, @Valid @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(customerService.updateStatus(id, request.getNewStatus(), request.getChangedBy()));
    }

    @GetMapping("/{id}/status-history")
    public ResponseEntity<List<StatusHistoryResponse>> getStatusHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(customerService.getStatusHistory(id));
    }
}