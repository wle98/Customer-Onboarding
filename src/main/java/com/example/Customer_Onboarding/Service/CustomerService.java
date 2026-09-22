package com.example.Customer_Onboarding.Service;

import com.example.Customer_Onboarding.DTO.CustomerRequest;
import com.example.Customer_Onboarding.DTO.CustomerResponse;
import com.example.Customer_Onboarding.DTO.StatusHistoryResponse;
import com.example.Customer_Onboarding.Entity.Customer;
import com.example.Customer_Onboarding.Entity.OnboardingStatus;
import com.example.Customer_Onboarding.Entity.OnboardingStatusHistory;
import com.example.Customer_Onboarding.Exception.DuplicateCustomerException;
import com.example.Customer_Onboarding.Exception.ResourceNotFoundException;
import com.example.Customer_Onboarding.Repository.CustomerRepository;
import com.example.Customer_Onboarding.Repository.OnboardingStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OnboardingStatusHistoryRepository statusHistoryRepository;

    @Autowired
    private NotificationService notificationService;

    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateCustomerException("Customer with email " + request.getEmail() + " already exists");
        }
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setBusinessType(request.getBusinessType());
        return toResponse(customerRepository.save(customer));
    }

    public CustomerResponse getCustomerResponseById(UUID id) {
        return toResponse(getCustomerById(id));
    }

    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        Customer existing = getCustomerById(id);
        existing.setName(request.getName());
        existing.setPhone(request.getPhone());
        existing.setAddress(request.getAddress());
        existing.setBusinessType(request.getBusinessType());
        return toResponse(customerRepository.save(existing));
    }

    public void deleteCustomer(UUID id) {
        Customer existing = getCustomerById(id);
        customerRepository.delete(existing);
    }

    // status

    public CustomerResponse updateStatus(UUID id, OnboardingStatus newStatus, String changedBy) {
        Customer customer = getCustomerById(id);
        OnboardingStatus oldStatus = customer.getStatus();

        if (oldStatus == newStatus) {
            return toResponse(customer);
        }

        customer.setStatus(newStatus);
        customerRepository.save(customer);

        OnboardingStatusHistory history = new OnboardingStatusHistory();
        history.setCustomer(customer);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedBy(changedBy);

        statusHistoryRepository.save(history);
        notificationService.notifyStatusChanged(customer, oldStatus, newStatus);
        if (newStatus == OnboardingStatus.COMPLETE) {
            notificationService.notifyOnboardingCompleted(customer);
        }

        return toResponse(customer);
    }

    public List<StatusHistoryResponse> getStatusHistory(UUID id) {
        getCustomerById(id); // validates customer exists, throws 404 if not
        return statusHistoryRepository.findByCustomerIdOrderByChangedAtDesc(id)
                .stream()
                .map(h -> new StatusHistoryResponse(
                        h.getId(),
                        h.getCustomer().getId(),
                        h.getOldStatus(),
                        h.getNewStatus(),
                        h.getChangedBy(),
                        h.getChangedAt()
                ))
                .toList();
    }

    // toResponse

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getBusinessType(),
                customer.getRegistrationDate(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}