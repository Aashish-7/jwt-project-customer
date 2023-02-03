package com.jwtproject.auth.dto;

public class CustomerDto {

    private String customerName;

    private String customerEmail;

    private String password;

    private String currentPassword;

    private String confirmPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerEmail(String email){
        this.customerEmail = email;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
