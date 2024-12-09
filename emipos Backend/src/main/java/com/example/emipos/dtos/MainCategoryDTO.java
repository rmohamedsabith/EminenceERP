package com.example.emipos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryDTO {

    private String name;
    private String description;
    private Date createdDate;
    private String creator;
    private Boolean active;

}
