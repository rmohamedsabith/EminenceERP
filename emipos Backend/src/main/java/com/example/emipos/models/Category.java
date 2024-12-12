package com.example.emipos.models;

import com.example.emipos.models.MainCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE category SET active = false WHERE id = ?") // Soft delete SQL
public class Category {

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
    @Column(name = "creator", length = 45, updatable = false)
    private String creator;

    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    private Date createdDate;

    @NotNull(message = "Active status cannot be null")
    @Column(name = "active")
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "mainCategoryId", referencedColumnName = "id", nullable = true)
    private MainCategory mainCategory;
}
