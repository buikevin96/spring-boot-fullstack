package com.amigoscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsCustomerById(Integer id);
    boolean existsCustomersByEmail(String email);
    Optional<Customer> findCustomerByEmail(String email);
}
