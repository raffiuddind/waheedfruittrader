package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.SupplierMapper;
import com.waheedfruittrader.model.dto.SupplierDTO;
import com.waheedfruittrader.model.entity.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Supplier management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;

    public List<SupplierDTO> getAllSuppliers() {
        return supplierMapper.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<SupplierDTO> searchSuppliers(String keyword, Boolean active) {
        return supplierMapper.findBySearch(keyword, active).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierMapper.findById(id);
        if (supplier == null) throw new ResourceNotFoundException("Supplier", id);
        return toDTO(supplier);
    }

    @Transactional
    public SupplierDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = toEntity(dto);
        supplier.setActive(true);
        supplier.setOutstandingBalance(BigDecimal.ZERO);
        supplierMapper.insert(supplier);
        return toDTO(supplier);
    }

    @Transactional
    public SupplierDTO updateSupplier(Long id, SupplierDTO dto) {
        if (supplierMapper.findById(id) == null) throw new ResourceNotFoundException("Supplier", id);
        dto.setId(id);
        supplierMapper.update(toEntity(dto));
        return getSupplierById(id);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        if (supplierMapper.findById(id) == null) throw new ResourceNotFoundException("Supplier", id);
        supplierMapper.deleteById(id);
    }

    private SupplierDTO toDTO(Supplier s) {
        return SupplierDTO.builder().id(s.getId()).name(s.getName()).phone(s.getPhone())
                .email(s.getEmail()).address(s.getAddress()).city(s.getCity())
                .notes(s.getNotes()).outstandingBalance(s.getOutstandingBalance())
                .active(s.getActive()).createdAt(s.getCreatedAt()).build();
    }

    private Supplier toEntity(SupplierDTO dto) {
        return Supplier.builder().id(dto.getId()).name(dto.getName()).phone(dto.getPhone())
                .email(dto.getEmail()).address(dto.getAddress()).city(dto.getCity())
                .notes(dto.getNotes()).outstandingBalance(dto.getOutstandingBalance())
                .active(dto.getActive() != null ? dto.getActive() : true).build();
    }
}
