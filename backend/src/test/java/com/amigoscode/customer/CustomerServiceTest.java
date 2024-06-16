package com.amigoscode.customer;

import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.RequestValidationException;
import com.amigoscode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService underTest;
    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
    }

    @Test
    void itShouldGetAllCustomers() {
        // When
        underTest.getAllCustomers();

        // Then
        verify(customerDao).selectALlCustomers();
    }

    @Test
    void itShouldGetCustomerById() {
        // Given
        int id = 1;
        Customer customer = new Customer(
                id,
                "Kevin",
                "k@gmal.com",
                22,
                Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        // When
        Customer actual = underTest.getCustomerById(1);

        // Then
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void itShouldThrowWhenGetCustomerByIdNotFound() {
        // Given
        int id = 10;

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() ->  underTest.getCustomerById(10))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("customer with id %s not found".formatted(id));
    }

    @Test
    void itShouldAddCustomer() {
        // Given
        String email = "k@gmal.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(false);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Kevin",
                email,
                22,
                Gender.MALE
        );
        // When
        underTest.addCustomer(request);
        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(
                Customer.class
        );
        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(request.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(request.age());

//        assertThatThrownBy(() ->  underTest.addCustomer())
//                .isInstanceOf(DuplicateResourceException.class)
//                .hasMessageContaining("Email already exists");
    }

    @Test
    void itShouldNotAddCustomerAndThrowWhenEmailExists() {
        // Given
        String email = "k@gmal.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(true);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Kevin",
                email,
                22,
                Gender.MALE
        );
        // When
        assertThatThrownBy(() -> underTest.addCustomer(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Email already exists");

        // Then

        verify(customerDao, Mockito.never()).insertCustomer(Mockito.any());

    }

    @Test
    void itShouldDeleteCustomerById() {
        // Given
        int id = 1;
        when(customerDao.existsPersonWithId(id)).thenReturn(true);

        // When
        underTest.deleteCustomerById(id);

        // Then
        verify(customerDao).deleteCustomerById(id);

    }

    @Test
    void itShouldThrowWhenCustomerNotExistsDeleteCustomerById() {
        // Given
        int id = 100;
        when(customerDao.existsPersonWithId(id)).thenReturn(false);

        // When
        assertThatThrownBy(() -> underTest.deleteCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Customer with id %s not found".formatted(id));

        // Then
        verify(customerDao, Mockito.never()).deleteCustomerById(Mockito.any());
    }

    @Test
    void itShouldUpdateCustomer() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        String newEmail = "alexandro@amigoscode.com";

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest("Alexandro", newEmail, 23);

        when(customerDao.existsPersonWithEmail(newEmail)).thenReturn(false);

        // When
        underTest.updateCustomer(id, updateRequest);

        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void itShouldUpdateCustomerName() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest =
                new CustomerUpdateRequest(
                        "Alexandro",
                        null,
                        null);

        // When
        underTest.updateCustomer(id, updateRequest);

        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(updateRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomer.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void itShouldUpdateCustomerEmail() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        String newEmail = "k@email.com";
        CustomerUpdateRequest updateRequest =
                new CustomerUpdateRequest(
                        null,
                        newEmail,
                        null);

        // When
        underTest.updateCustomer(id, updateRequest);

        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomer.getEmail()).isEqualTo(updateRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(customer.getAge());
    }

    @Test
    void itShouldUpdateCustomerAge() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest =
                new CustomerUpdateRequest(
                        null,
                        null,
                        18);

        // When
        underTest.updateCustomer(id, updateRequest);

        // Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getName()).isEqualTo(customer.getName());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(capturedCustomer.getAge()).isEqualTo(updateRequest.age());
    }

    @Test
    void itShouldThrowWhenUpdateCustomerEmailAlreadyTaken() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        String newEmail = "k@email.com";
        CustomerUpdateRequest updateRequest =
                new CustomerUpdateRequest(
                        null,
                        newEmail,
                        null);

        Mockito.when(customerDao.existsPersonWithEmail(newEmail)).thenReturn(true);

        // When
        assertThatThrownBy(() -> underTest.updateCustomer(id, updateRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Email already taken");

        // Then
        verify(customerDao, never()).updateCustomer(any());

    }

    @Test
    void itShouldNotUpdateCustomerWhenNoChanges() {
        // Given
        int id = 10;
        Customer customer = new Customer(id, "Alex", "alex@gmail.com", 19, Gender.MALE);
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(customer.getName(), customer.getEmail(), customer.getAge());

        // When
        assertThatThrownBy(() -> underTest.updateCustomer(id, updateRequest))
                .isInstanceOf(RequestValidationException.class)
                .hasMessageContaining("no data changes found");

        // Then
        verify(customerDao, never()).updateCustomer(any());

    }

}