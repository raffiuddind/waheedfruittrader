package com.waheedfruittrader.service;

import com.waheedfruittrader.exception.BusinessException;
import com.waheedfruittrader.exception.ResourceNotFoundException;
import com.waheedfruittrader.mapper.FruitMapper;
import com.waheedfruittrader.model.dto.FruitDTO;
import com.waheedfruittrader.model.entity.Fruit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Fruit management operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FruitService {

    private final FruitMapper fruitMapper;

    /**
     * Get all fruits.
     */
    public List<FruitDTO> getAllFruits() {
        return fruitMapper.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Search fruits by keyword, type, and category.
     */
    public List<FruitDTO> searchFruits(String keyword, String type, String category, Boolean active) {
        return fruitMapper.findBySearch(keyword, type, category, active).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get fruit by ID.
     */
    public FruitDTO getFruitById(Long id) {
        Fruit fruit = fruitMapper.findById(id);
        if (fruit == null) {
            throw new ResourceNotFoundException("Fruit", id);
        }
        return toDTO(fruit);
    }

    /**
     * Create a new fruit.
     */
    @Transactional
    public FruitDTO createFruit(FruitDTO dto) {
        if (fruitMapper.findByName(dto.getName()) != null) {
            throw new BusinessException("Fruit with name '" + dto.getName() + "' already exists");
        }
        Fruit fruit = toEntity(dto);
        fruit.setActive(true);
        fruitMapper.insert(fruit);
        log.info("Created fruit with ID: {}", fruit.getId());
        return toDTO(fruit);
    }

    /**
     * Update an existing fruit.
     */
    @Transactional
    public FruitDTO updateFruit(Long id, FruitDTO dto) {
        Fruit existing = fruitMapper.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Fruit", id);
        }
        Fruit duplicate = fruitMapper.findByName(dto.getName());
        if (duplicate != null && !duplicate.getId().equals(id)) {
            throw new BusinessException("Fruit with name '" + dto.getName() + "' already exists");
        }
        dto.setId(id);
        Fruit fruit = toEntity(dto);
        fruitMapper.update(fruit);
        log.info("Updated fruit with ID: {}", id);
        return getFruitById(id);
    }

    /**
     * Delete a fruit by ID.
     */
    @Transactional
    public void deleteFruit(Long id) {
        if (fruitMapper.findById(id) == null) {
            throw new ResourceNotFoundException("Fruit", id);
        }
        fruitMapper.deleteById(id);
        log.info("Deleted fruit with ID: {}", id);
    }

    private FruitDTO toDTO(Fruit fruit) {
        return FruitDTO.builder()
                .id(fruit.getId())
                .name(fruit.getName())
                .type(fruit.getType())
                .category(fruit.getCategory())
                .unit(fruit.getUnit())
                .purchasePrice(fruit.getPurchasePrice())
                .sellingPrice(fruit.getSellingPrice())
                .description(fruit.getDescription())
                .imageUrl(fruit.getImageUrl())
                .active(fruit.getActive())
                .createdAt(fruit.getCreatedAt())
                .build();
    }

    private Fruit toEntity(FruitDTO dto) {
        return Fruit.builder()
                .id(dto.getId())
                .name(dto.getName())
                .type(dto.getType())
                .category(dto.getCategory())
                .unit(dto.getUnit())
                .purchasePrice(dto.getPurchasePrice())
                .sellingPrice(dto.getSellingPrice())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }
}
