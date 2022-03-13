package com.curewell.invoicemanagement.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curewell.invoicemanagement.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	User findByEmail(String email);

}
