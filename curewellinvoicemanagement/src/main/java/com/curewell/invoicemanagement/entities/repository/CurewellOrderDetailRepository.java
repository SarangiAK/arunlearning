package com.curewell.invoicemanagement.entities.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curewell.invoicemanagement.entities.CurewellOrderdetail;

public interface CurewellOrderDetailRepository extends JpaRepository<CurewellOrderdetail, Long> {
	
}
