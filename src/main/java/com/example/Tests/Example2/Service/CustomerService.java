package com.example.Tests.Example2.Service;

import com.example.Tests.Example2.ApplicationConfig.Customer;

import java.io.IOException;
import java.util.List;

/**
 * @author Artur Kuzmik on 18.29.5
 */

public interface CustomerService {

    void addCustomer() throws IOException;

    void editCustomer() throws IOException;

    void deleteCustomer() throws IOException;

    void sortDateSurname();

    void debtors();

    void allPrice();

    Customer find(long customerId);

    List<Customer> findAll();

    void printCustomers();
}
