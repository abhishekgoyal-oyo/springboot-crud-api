package com.crudapi.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "salary")
    private float salary;

    @Column(name = "designation")
    private String designation;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "date_of_joining")
    private Date dateOfJoining;

    @Column(name = "phone")
    private long phone;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    public Employee() {

    }

    public Employee(long id,
                    Department department,
                    String name,
                    String email,
                    String address,
                    String gender,
                    float salary,
                    String designation,
                    Date dateOfBirth,
                    Date dateOfJoining,
                    long phone) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}