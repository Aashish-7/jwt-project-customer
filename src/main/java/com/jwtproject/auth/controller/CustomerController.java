package com.jwtproject.auth.controller;

import com.jwtproject.auth.dto.CustomerDto;
import com.jwtproject.auth.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody CustomerDto customerDto) throws Exception {
        return ResponseEntity.ok(customerService.addCustomer(customerDto));
    }


    @PutMapping("/updateCustomer/{id}")
    public String updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long id){
        return customerService.updateCustomer(customerDto, id);
    }

    @PostMapping("/changePassword/{id}")
    public String changePassword(@RequestBody CustomerDto customerDto, @PathVariable Long id) throws Exception {
        return customerService.changePassword(customerDto, id);
    }

    @GetMapping("/getAllAcByCustomer")
    public List<Object> getAllAcByCustomer(){
        return customerService.getAllACByCustomer();
    }
}
