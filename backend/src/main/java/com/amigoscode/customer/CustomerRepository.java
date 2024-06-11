package com.amigoscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsCustomerById(Integer id);
    boolean existsCustomersByEmail(String email);
}
