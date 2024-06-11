package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.RequestValidationException;
import com.amigoscode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectALlCustomers();
    }

    public Customer getCustomerById(Integer id){
        return customerDao.selectCustomerById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "customer with id %s not found".formatted(id)
                        ));
    }

    public void addCustomer(
            CustomerRegistrationRequest customerRegistrationRequest){
        // Check if email exists
        if (customerDao.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }
        // If not, add
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer id){
        if (!customerDao.existsPersonWithId(id)){
            throw new ResourceNotFoundException(
                    "Customer with id %s not found".formatted(id)
            );
        }
        customerDao.deleteCustomerById(id);
    }

    public void updateCustomer(Integer id, CustomerUpdateRequest updateRequest){
        Customer customer = getCustomerById(id);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            //customerDao.insertCustomer(customer);
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
            if (customerDao.existsPersonWithEmail(updateRequest.email())){
                throw new DuplicateResourceException(
                        "Email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            //customerDao.insertCustomer(customer);
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            //customerDao.insertCustomer(customer);
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }
        customerDao.updateCustomer(customer);
    }
}
