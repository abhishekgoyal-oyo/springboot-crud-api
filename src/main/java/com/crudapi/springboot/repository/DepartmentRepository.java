package com.crudapi.springboot.repository;

import com.crudapi.springboot.model.Department;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

@Reference
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
