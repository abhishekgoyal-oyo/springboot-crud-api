package com.crudapi.springboot.dtos;

import com.crudapi.springboot.model.Department;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String name;
    private String email;
    private String address;
    private String gender;
    private float salary;
    private String designation;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private long phone;
    private Department department;
}
