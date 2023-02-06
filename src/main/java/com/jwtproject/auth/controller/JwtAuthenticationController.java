package com.jwtproject.auth.controller;

import com.jwtproject.auth.dao.CustomerRepository;
import com.jwtproject.auth.dao.JwtResponseRepository;
import com.jwtproject.auth.model.JwtRequest;
import com.jwtproject.auth.model.JwtResponse;
import com.jwtproject.auth.service.CustomerServiceImpl;
import com.jwtproject.config.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationProvider authenticationManager;
    private final JwtTokenUtils jwtTokenUtil;
    private CustomerServiceImpl customerService;
    private final CustomerRepository customerRepository;
    private JwtResponseRepository jwtResponseRepository;

    @Autowired
    public JwtAuthenticationController(AuthenticationProvider authenticationManager, JwtTokenUtils jwtTokenUtil, CustomerServiceImpl customerService, CustomerRepository customerRepository, JwtResponseRepository jwtResponseRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.jwtResponseRepository = jwtResponseRepository;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println(authenticationRequest.getCustomerEmail());
        System.out.println(authenticationRequest.getPassword());
        authenticate(authenticationRequest.getCustomerEmail(), authenticationRequest.getPassword());

        System.out.println(authenticationRequest.getCustomerEmail());
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(customerRepository.findByCustomerEmail(authenticationRequest.getCustomerEmail()));
        JwtResponse jwtResponse = new JwtResponse();
        System.out.println(token);
        jwtResponse.setJwtToken(token);
        jwtResponse.setEmail(authenticationRequest.getCustomerEmail());
        jwtResponseRepository.save(jwtResponse);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}