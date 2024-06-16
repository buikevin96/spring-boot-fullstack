package com.amigoscode.customer;

import com.amigoscode.AbstractTestcontainers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

class CustomerJPADataAccessServiceTest extends AbstractTestcontainers {

    private CustomerJPADataAccessService underTest;
    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void itShouldSelectALlCustomers() {
        // When
        underTest.selectALlCustomers();

        // Then
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    void itShouldSelectCustomerById() {
        // Given
        int id = 1;

        // When
        underTest.selectCustomerById(id);

        // Then
        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    void itShouldInsertCustomer() {
        // Given
        Customer customer = new Customer(
                1,
                "Kevin",
                "k@gmail.com",
                24,
                Gender.MALE);

        // When
        underTest.insertCustomer(customer);

        // Then
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    void itShouldExistsPersonWithEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20,
                Gender.MALE);

        // When
        underTest.existsPersonWithEmail(email);

        // Then
        Mockito.verify(customerRepository).existsCustomersByEmail(email);
    }

    @Test
    void itShouldExistsPersonWithId() {
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        int id = 1;
        Customer customer = new Customer(
                id,
                FAKER.name().fullName(),
                email,
                20,
                Gender.MALE);


        // When
        underTest.existsPersonWithId(id);

        // Then
        Mockito.verify(customerRepository).existsCustomerById(id);
    }

    @Test
    void itShouldDeleteCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        int id = 1;
        Customer customer = new Customer(
                id,
                FAKER.name().fullName(),
                email,
                20,
                Gender.MALE);

        // When
        underTest.deleteCustomerById(id);

        // Then
        Mockito.verify(customerRepository).deleteById(id);
    }

    @Test
    void itShouldUpdateCustomer() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                1,
                FAKER.name().fullName(),
                email,
                20,
                Gender.MALE);


        // When
        Customer update = new Customer();
        update.setId(2);
        update.setAge(22);
        update.setName("Kevin");
        update.setEmail(UUID.randomUUID().toString());

        underTest.updateCustomer(update);

        // Then
        Mockito.verify(customerRepository).save(update);
    }
}