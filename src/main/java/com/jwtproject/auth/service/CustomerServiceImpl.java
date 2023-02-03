package com.jwtproject.auth.service;

import com.jwtproject.auth.dao.CustomerRepository;
import com.jwtproject.auth.dto.CustomerDto;
import com.jwtproject.auth.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    CustomerRepository customerRepository;

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerEmail(username);
        if (customer == null){
            throw new UsernameNotFoundException("Customer with " + username + "does not exists");
        }
        return customer;
    }

    public Customer addCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        return customerRepository.save(customer);
    }

    public String changePassword(CustomerDto customerDto, Long id) throws Exception {
        if (customerDto.getCurrentPassword().equals(customerDto.getPassword())) {
            return "current password and new password are same";
        }
        if (passwordEncoder.matches(customerDto.getCurrentPassword(), customerRepository.findById(id).get().getPassword()) && customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            Customer vendor = customerRepository.findById(id).get();
            vendor.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            customerRepository.save(vendor);
            return "Password changed successfully";
        } else {
            return "Enter valid password";
        }
    }

    public String updateCustomer(CustomerDto customerDto, Long id){
        if (customerRepository.findById(id).isPresent()){
            Customer customer = customerRepository.findById(id).get();
            customer.setCustomerEmail(customerDto.getCustomerEmail());
            customer.setCustomerName(customerDto.getCustomerName());
            customerRepository.save(customer);
        }
        else {
            System.out.println("customer not found");
        }
        return "customer successfully updated";
    }

    public Boolean deleteCustomer(){

        return Boolean.TRUE;
    }

    public List<Object> getAllACByCustomer(){
        String url = "http://localhost:8080/products/ac/getAllAcByVendor";
        RestTemplate restTemplate = new RestTemplate();
        Object[] allAC = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(allAC);
    }
}
