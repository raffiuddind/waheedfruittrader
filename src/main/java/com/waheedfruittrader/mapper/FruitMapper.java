package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis mapper interface for Fruit operations.
 */
@Mapper
public interface FruitMapper {

    List<Fruit> findAll();

    List<Fruit> findBySearch(@Param("keyword") String keyword,
                             @Param("type") String type,
                             @Param("category") String category,
                             @Param("active") Boolean active);

    Fruit findById(Long id);

    Fruit findByName(String name);

    int insert(Fruit fruit);

    int update(Fruit fruit);

    int deleteById(Long id);

    int count();
}
