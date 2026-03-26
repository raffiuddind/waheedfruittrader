package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.BusinessException;
import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.CustomerMapper;
import com.waheedfruittrader.model.dto.CustomerDTO;
import com.waheedfruittrader.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Customer management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> searchCustomers(String keyword, Boolean active) {
        return customerMapper.findBySearch(keyword, active).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer", id);
        }
        return toDTO(customer);
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = toEntity(dto);
        customer.setActive(true);
        customer.setOutstandingBalance(BigDecimal.ZERO);
        customerMapper.insert(customer);
        log.info("Created customer with ID: {}", customer.getId());
        return toDTO(customer);
    }

    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        if (customerMapper.findById(id) == null) {
            throw new ResourceNotFoundException("Customer", id);
        }
        dto.setId(id);
        customerMapper.update(toEntity(dto));
        return getCustomerById(id);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        if (customerMapper.findById(id) == null) {
            throw new ResourceNotFoundException("Customer", id);
        }
        customerMapper.deleteById(id);
        log.info("Deleted customer with ID: {}", id);
    }

    private CustomerDTO toDTO(Customer c) {
        return CustomerDTO.builder()
                .id(c.getId()).name(c.getName()).phone(c.getPhone())
                .email(c.getEmail()).address(c.getAddress()).city(c.getCity())
                .notes(c.getNotes()).creditLimit(c.getCreditLimit())
                .outstandingBalance(c.getOutstandingBalance())
                .active(c.getActive()).createdAt(c.getCreatedAt())
                .build();
    }

    private Customer toEntity(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId()).name(dto.getName()).phone(dto.getPhone())
                .email(dto.getEmail()).address(dto.getAddress()).city(dto.getCity())
                .notes(dto.getNotes()).creditLimit(dto.getCreditLimit())
                .outstandingBalance(dto.getOutstandingBalance())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }
}
