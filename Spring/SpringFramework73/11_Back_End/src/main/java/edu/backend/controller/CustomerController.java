package edu.backend.controller;

import edu.backend.dto.CustomerDTO;
import edu.backend.service.custom.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

//    @Autowired
//    private CustomerService customerService;
//    private CustomerService customerService;
private final CustomerServiceImpl customerService;//why

//    public CustomerController (CustomerService customerService) {
//        this.customerService = customerService;
//    }

    //@RequiredArgs cons dammama con implementation oni na

    @PostMapping
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        System.out.println("saveCustomer");
//        System.out.println(customerDTO.getCid());
//        System.out.println(customerDTO.getNcame());
//        System.out.println(customerDTO.getCaddress());
    }
}