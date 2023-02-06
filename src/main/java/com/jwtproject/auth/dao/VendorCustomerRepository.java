package com.jwtproject.auth.dao;

import com.jwtproject.auth.model.VendorCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorCustomerRepository extends JpaRepository<VendorCustomer, Long> {
}