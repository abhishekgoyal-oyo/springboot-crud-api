package com.crudapi.springboot.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String name;
    private String address;
    private String sector;
    private long phone;
    private String email;
}
