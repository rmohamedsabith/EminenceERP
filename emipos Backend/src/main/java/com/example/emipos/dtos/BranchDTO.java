package com.example.emipos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BranchDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean isMain;
    private String creator;
    private Date createdDate;
    private Boolean active=true;
}
