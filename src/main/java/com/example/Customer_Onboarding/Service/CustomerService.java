package com.example.Customer_Onboarding.Service;

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

    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new DuplicateCustomerException("Customer with email " + customer.getEmail() + " already exists");
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(UUID id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        existing.setName(updatedCustomer.getName());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setAddress(updatedCustomer.getAddress());
        existing.setBusinessType(updatedCustomer.getBusinessType());
        return customerRepository.save(existing);
    }

    public void deleteCustomer(UUID id) {
        Customer existing = getCustomerById(id);
        customerRepository.delete(existing);
    }

    // status

    public Customer updateStatus(UUID id, OnboardingStatus newStatus, String changedBy) {
        Customer customer = getCustomerById(id);
        OnboardingStatus oldStatus = customer.getStatus();

        // prevents logging in old status and new status if they are the same
        if (oldStatus == newStatus) {
            return customer;
        }

        customer.setStatus(newStatus);
        customerRepository.save(customer);

        OnboardingStatusHistory history = new OnboardingStatusHistory();
        history.setCustomer(customer);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedBy(changedBy);
        statusHistoryRepository.save(history);

        return customer;
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

}