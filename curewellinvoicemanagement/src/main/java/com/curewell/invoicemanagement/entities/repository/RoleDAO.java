package com.curewell.invoicemanagement.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curewell.invoicemanagement.entities.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {

}
