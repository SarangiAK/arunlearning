package com.curewell.invoicemanagement.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curewell.invoicemanagement.entities.CurewellProduct;

public interface CurewellProductDAO extends JpaRepository<CurewellProduct, Integer> {

}
