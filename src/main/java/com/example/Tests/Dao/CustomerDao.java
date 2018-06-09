package com.example.Tests.Dao;

import com.example.Tests.ApplicationConfig.Customer;

import java.util.List;

/**
 * @author Artur Kuzmik on 18.29.5
 */

public interface CustomerDao {

    void addCustomer(Customer customer);

    void editCustomer(Customer customer, Long customerId);

    void deleteCustomer(int customerId);

    List<Customer> debtors();

    Customer find(long customerId);

    List<Customer> findAll();
}
