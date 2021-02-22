package com.crudapi.springboot.service;

import com.crudapi.springboot.dtos.EmployeeDto;
import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.mapper.EmployeeMapper;
import com.crudapi.springboot.model.Employee;
import com.crudapi.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        List<Employee> companies = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>(companies.size());
        for (Employee employee : companies) {
            employeeDtos.add(EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee));
        }
        return employeeDtos;
    }

    public ResponseEntity<EmployeeDto> findById(Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
        return ResponseEntity.ok().body(employeeDto);
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    public ResponseEntity<EmployeeDto> update(Long employeeId, EmployeeDto employeeDto)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setDepartment(employeeDto.getDepartment());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setGender(employeeDto.getGender());
        employee.setSalary(employeeDto.getSalary());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setPhone(employeeDto.getPhone());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(EmployeeMapper.INSTANCE.employeeToEmployeeDto(updatedEmployee));
    }

    public Map<String, Boolean> delete(Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}