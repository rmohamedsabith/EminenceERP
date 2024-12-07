package com.example.emipos.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BrandDTO {
    private String name;
    private String description;
    private String creator;
    private Date createdDate;
    private Boolean active;
}
