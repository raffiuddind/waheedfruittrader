package com.waheedfruittrader.mapper;

import com.waheedfruittrader.model.entity.Transaction;
import com.waheedfruittrader.model.entity.TransactionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * MyBatis mapper interface for Transaction operations.
 */
@Mapper
public interface TransactionMapper {

    List<Transaction> findAll();

    List<Transaction> findBySearch(@Param("type") String type,
                                   @Param("status") String status,
                                   @Param("customerId") Long customerId,
                                   @Param("supplierId") Long supplierId,
                                   @Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate);

    Transaction findById(Long id);

    Transaction findByTransactionNumber(String transactionNumber);

    int insert(Transaction transaction);

    int update(Transaction transaction);

    int deleteById(Long id);

    // Transaction items
    int insertItem(TransactionItem item);

    List<TransactionItem> findItemsByTransactionId(Long transactionId);

    int deleteItemsByTransactionId(Long transactionId);

    String generateTransactionNumber(@Param("type") String type);
}
