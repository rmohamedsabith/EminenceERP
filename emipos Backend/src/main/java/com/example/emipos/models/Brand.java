package com.example.emipos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 45, message = "Name must be between 1 and 45 characters")
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 200, message = "Description must be less than 200 characters")
    @Column(name = "description", length = 200)
    private String description;

    @CreatedBy
    @Size(max = 45, message = "Creator name must be less than 45 characters")
    @Column(name = "creator", length = 45)
    private String creator;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @NotNull(message = "Active status cannot be null")
    @Column(name = "active")
    private Boolean active=true;

    // Optionally, you can set the createdDate before persisting the entity
    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }
}
