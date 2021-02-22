package com.crudapi.springboot.mapper;

import com.crudapi.springboot.dtos.DepartmentDto;
import com.crudapi.springboot.model.Department;
import com.crudapi.springboot.service.CompanyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { CompanyService.class })
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    @Mapping(source = "companyId", target = "company.id")
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
    @Mapping(target="companyId", source="company.id")
    DepartmentDto departmentToDepartmentDto(Department department);
}
