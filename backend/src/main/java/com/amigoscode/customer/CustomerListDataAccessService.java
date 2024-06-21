package com.amigoscode.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository(value = "list")
public class CustomerListDataAccessService implements CustomerDao{

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(
                1,
                "Alex",
                "Alex@gmail.com",
                "password",
                21,
                Gender.MALE);
        customers.add(alex);

        Customer jamila = new Customer(
                2,
                "Jamila",
                "jamila@gmail.com",
                "password",
                29,
                Gender.MALE);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectALlCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Optional<Customer> selectUserByEmail(String email) {
        return customers
                .stream()
                .filter(c -> c.getUsername().equals(email))
                .findFirst();
    }


}
