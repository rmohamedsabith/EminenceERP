package com.example.emipos.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplierDTO {
    private Integer id;
    private String name;
    private String description;
    private String companyName;
    private String contactPersonName;
    private String contactNumber;
    private String bank;
    private String branch;
    private String accountNumber;
    private String address;
    private String fax;
    private String email;
    private String web;
    private String reason;
    private Boolean active=true;
}
