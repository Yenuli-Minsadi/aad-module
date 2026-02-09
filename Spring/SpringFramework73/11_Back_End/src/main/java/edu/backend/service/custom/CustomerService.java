package edu.backend.service.custom;

import edu.backend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);
    public void deleteCustomer(String customerId);
    public void getAllCustomer(CustomerDTO customerDTO);
}

