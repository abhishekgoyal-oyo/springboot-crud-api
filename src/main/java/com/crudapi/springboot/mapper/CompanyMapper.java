package com.crudapi.springboot.mapper;

import com.crudapi.springboot.dtos.CompanyDto;
import com.crudapi.springboot.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    Company companyDtoToCompany(CompanyDto companyDto);
    CompanyDto companyToCompanyDto(Company company);
}
