package com.amigoscode.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {

    private CustomerRowMapper underTest;

    @Mock
    private RowMapper<Customer> rowMapper;


    @Test
    void itShouldMapRow() throws SQLException {
        // Given
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("age")).thenReturn(19);
        when(resultSet.getString("name")).thenReturn("kev");
        when(resultSet.getString("email")).thenReturn("kev@email.com");

        // When
        Customer actual = customerRowMapper.mapRow(null, 1);

        // Then
        Customer expected = new Customer(
                1,
                "kev",
                "kev@email.com",
                19
        );

        assertThat(actual).isEqualTo(expected);
    }
}