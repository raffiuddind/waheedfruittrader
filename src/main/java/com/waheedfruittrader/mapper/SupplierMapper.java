package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis mapper interface for Supplier operations.
 */
@Mapper
public interface SupplierMapper {

    List<Supplier> findAll();

    List<Supplier> findBySearch(@Param("keyword") String keyword,
                                @Param("active") Boolean active);

    Supplier findById(Long id);

    int insert(Supplier supplier);

    int update(Supplier supplier);

    int deleteById(Long id);

    int count();
}
