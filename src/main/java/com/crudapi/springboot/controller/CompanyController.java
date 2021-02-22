package com.crudapi.springboot.controller;

import com.crudapi.springboot.dtos.CompanyDto;
import com.crudapi.springboot.dtos.DepartmentDto;
import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<CompanyDto> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        return companyService.findById(companyId);
    }

    @GetMapping("/companies/{id}/departments")
    public List<DepartmentDto> getDepartmentByCompanyId(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        return companyService.getDepartments(companyId);
    }

    @PostMapping("/companies")
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.save(companyDto);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable(value = "id") Long companyId,
                                                   @RequestBody CompanyDto companyDto) throws ResourceNotFoundException {
        return companyService.update(companyId, companyDto);
    }

    @DeleteMapping("/companies/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        return companyService.delete(companyId);
    }
}