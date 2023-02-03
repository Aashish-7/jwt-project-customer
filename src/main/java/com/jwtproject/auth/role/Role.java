package com.jwtproject.auth.role;

public enum Role {

    VENDOR("vendor"),

    CUSTOMER("customer");

    private final String name;


    Role(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
