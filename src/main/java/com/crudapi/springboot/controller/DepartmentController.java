package com.crudapi.springboot.controller;

import com.crudapi.springboot.dtos.DepartmentDto;
import com.crudapi.springboot.dtos.EmployeeDto;
import com.crudapi.springboot.exception.ResourceNotFoundException;
import com.crudapi.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        return departmentService.findById(departmentId);
    }

    @GetMapping("/departments/{id}/employees")
    public List<EmployeeDto> getEmployeesByDepartmentId(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        return departmentService.getEmployees(departmentId);
    }

    @PostMapping("/departments")
    public DepartmentDto createDepartment(@RequestBody DepartmentDto department) {
        return departmentService.save(department);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable(value = "id") Long departmentId,
                                                 @RequestBody DepartmentDto departmentDto) throws ResourceNotFoundException {
        return departmentService.update(departmentId, departmentDto);
    }

    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        return  departmentService.delete(departmentId);
    }
}