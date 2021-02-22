package com.crudapi.springboot.service;

import com.crudapi.springboot.dtos.DepartmentDto;
import com.crudapi.springboot.dtos.EmployeeDto;
import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.mapper.DepartmentMapper;
import com.crudapi.springboot.mapper.EmployeeMapper;
import com.crudapi.springboot.model.Company;
import com.crudapi.springboot.model.Department;
import com.crudapi.springboot.model.Employee;
import com.crudapi.springboot.repository.CompanyRepository;
import com.crudapi.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;


    public List<DepartmentDto> findAll() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>(departments.size());
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.INSTANCE.departmentToDepartmentDto(department));
        }
        return departmentDtos;
    }

    public ResponseEntity<DepartmentDto> findById(Long departmentId)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        DepartmentDto departmentDto = DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
        return ResponseEntity.ok().body(departmentDto);
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.INSTANCE.departmentDtoToDepartment(departmentDto);
        return DepartmentMapper.INSTANCE.departmentToDepartmentDto(departmentRepository.save(department));
    }

    public ResponseEntity<DepartmentDto> update(Long departmentId, DepartmentDto departmentDto)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        department.setName(departmentDto.getName());
        long companyId = departmentDto.getCompanyId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        department.setCompany(company);
        department.setEmail(departmentDto.getEmail());
        final Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.departmentToDepartmentDto(updatedDepartment));
    }

    public Map<String, Boolean> delete(Long departmentId) throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<EmployeeDto> getEmployees(Long departmentId) throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        Set<Employee> employees = department.getEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee));
        }
        return employeeDtos;
    }
}