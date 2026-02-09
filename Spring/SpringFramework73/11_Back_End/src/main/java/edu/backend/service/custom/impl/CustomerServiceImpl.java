package edu.backend.service.custom.impl;

import edu.backend.dto.CustomerDTO;
import edu.backend.dto.repository.CustomerRepository;
import edu.backend.entity.Customer;
import edu.backend.service.custom.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(
                customerDTO.getCid(),
                customerDTO.getName(),
                customerDTO.getAddress()
        ));
//        System.out.println("CustomerService.saveCustomer");
//        System.out.println(customerDTO.getCid());
//        System.out.println(customerDTO.getNcame());
//        System.out.println(customerDTO.getCaddress());

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(customerDTO.getCid(), customerDTO.getName(), customerDTO.getAddress()));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void getAllCustomer(CustomerDTO customerDTO) {
        customerRepository.findAll();
    }



}
