package com.crudapi.springboot.service;

import com.crudapi.springboot.dtos.CompanyDto;
import com.crudapi.springboot.dtos.DepartmentDto;
import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.mapper.CompanyMapper;
import com.crudapi.springboot.mapper.DepartmentMapper;
import com.crudapi.springboot.model.Company;
import com.crudapi.springboot.model.Department;
import com.crudapi.springboot.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDto> findAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>(companies.size());
        for (Company company : companies) {
            companyDtos.add(CompanyMapper.INSTANCE.companyToCompanyDto(company));
        }
        return companyDtos;
    }

    public ResponseEntity<CompanyDto> findById(Long companyId)
            throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        CompanyDto companyDto = CompanyMapper.INSTANCE.companyToCompanyDto(company);
        return ResponseEntity.ok().body(companyDto);
    }

    public CompanyDto save(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.companyDtoToCompany(companyDto);
        return CompanyMapper.INSTANCE.companyToCompanyDto(companyRepository.save(company));
    }

    public ResponseEntity<CompanyDto> update(Long companyId, CompanyDto companyDto)
            throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setEmail(companyDto.getEmail());
        company.setSector(companyDto.getSector());
        company.setPhone(companyDto.getPhone());
        final Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(CompanyMapper.INSTANCE.companyToCompanyDto(updatedCompany));
    }

    public Map<String, Boolean> delete(Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        companyRepository.delete(company);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<DepartmentDto> getDepartments(Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        Set<Department> departments = company.getDepartments();
        List<DepartmentDto> departmentDtos = new ArrayList<>(departments.size());
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.INSTANCE.departmentToDepartmentDto(department));
        }
        return departmentDtos;
    }
}
