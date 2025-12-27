package com.yms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yms.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
	
	@Query("SELECT COALESCE(SUM(r.totalAmount), 0) FROM Receipt r")
    Double getTotalRevenue();
}

