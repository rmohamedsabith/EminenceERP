package com.example.emipos.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(length = 100, nullable = false)
    private String companyName;

    @Column(length = 100, nullable = false)
    private String contactPersonName;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 100, nullable = false)
    private String bank;

    @Column(length = 50)
    private String branch;

    @Column(length = 20, nullable = false)
    private String accountNumber;

    @Column(length = 200)
    private String address;

    @Column(length = 20)
    private String fax;

    @Column(length = 45)
    private String email;

    @Column(length = 45)
    private String web;

    @Column(length = 45)
    private String reason;

    @CreatedBy
    @Column(name = "creator", length = 45, updatable = false)
    private String creator;

    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updatedBy", length = 45)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    private Boolean active=true;


}
