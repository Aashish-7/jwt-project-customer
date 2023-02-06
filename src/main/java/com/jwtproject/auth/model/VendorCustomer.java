package com.jwtproject.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vendorName;

    private String vendorEmail;

    private String password;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private com.jwtproject.auth.role.Role Role;
}
