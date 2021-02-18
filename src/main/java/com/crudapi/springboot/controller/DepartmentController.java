package com.crudapi.springboot.controller;

import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.model.Department;
import com.crudapi.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        return ResponseEntity.ok().body(department);
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Long departmentId,
                                                 @RequestBody Department departmentDetails) throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        department.setName(departmentDetails.getName());
        department.setCompany(departmentDetails.getCompany());
        department.setEmail(departmentDetails.getEmail());
        final Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
