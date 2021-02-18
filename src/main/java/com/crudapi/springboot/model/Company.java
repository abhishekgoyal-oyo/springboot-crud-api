package com.crudapi.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "sector")
    private String sector;

    @Column(name = "phone")
    private long phone;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();

    public  Company() {

    }

    public Company(long id, String name, String address, String sector, long phone, String email, Set<Department> departments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sector = sector;
        this.phone = phone;
        this.email = email;
        this.departments = departments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Department> getDepartments() { return departments; }

    public void setDepartments(Set<Department> departments) { this.departments = departments; }

}