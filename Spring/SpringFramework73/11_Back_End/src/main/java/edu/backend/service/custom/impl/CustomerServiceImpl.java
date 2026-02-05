package edu.backend.service.custom.impl;

import edu.backend.dto.CustomerDTO;
import edu.backend.dto.repository.CustomerRepository;
import edu.backend.entity.Customer;
import edu.backend.service.custom.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(
                customerDTO.getCid(),
                customerDTO.getNcame(),
                customerDTO.getCaddress()
        ));
//        System.out.println("CustomerService.saveCustomer");
//        System.out.println(customerDTO.getCid());
//        System.out.println(customerDTO.getNcame());
//        System.out.println(customerDTO.getCaddress());

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerDTO.getCid(), customerDTO.getNcame(), customerDTO.getCaddress();
    }

    @Override
    public void deleteCustomer(String customerId) {

    }

    @Override
    public void getAllCustomer(CustomerDTO customerDTO) {

    }
}
