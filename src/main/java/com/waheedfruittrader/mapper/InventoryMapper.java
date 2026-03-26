package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Inventory;
import com.waheedfruittrader.model.entity.InventoryLocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * MyBatis mapper interface for Inventory operations.
 */
@Mapper
public interface InventoryMapper {

    List<Inventory> findAll();

    List<Inventory> findLowStock();

    Inventory findByFruitAndLocation(@Param("fruitId") Long fruitId,
                                     @Param("locationId") Long locationId);

    Inventory findById(Long id);

    int insert(Inventory inventory);

    int update(Inventory inventory);

    int adjustQuantity(@Param("fruitId") Long fruitId,
                       @Param("locationId") Long locationId,
                       @Param("quantity") BigDecimal quantity);

    // Locations
    List<InventoryLocation> findAllLocations();

    InventoryLocation findLocationById(Long id);

    int insertLocation(InventoryLocation location);

    int updateLocation(InventoryLocation location);
}
