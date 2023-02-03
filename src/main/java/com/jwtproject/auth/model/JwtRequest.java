package com.jwtproject.auth.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private String customerEmail;

    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String customerEmail, String password) {
        this.customerEmail = customerEmail;
        this.password = password;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
