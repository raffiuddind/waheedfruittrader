package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis mapper interface for Customer operations.
 */
@Mapper
public interface CustomerMapper {

    List<Customer> findAll();

    List<Customer> findBySearch(@Param("keyword") String keyword,
                                @Param("active") Boolean active);

    Customer findById(Long id);

    Customer findByPhone(String phone);

    int insert(Customer customer);

    int update(Customer customer);

    int deleteById(Long id);

    int count();
}
