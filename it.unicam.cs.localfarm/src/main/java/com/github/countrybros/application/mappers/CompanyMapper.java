package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.company.CompanyDto;
import com.github.countrybros.application.models.requests.company.AddCompanyRequest;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.model.company.CompanyStatus;

public class CompanyMapper {
    public static Company toDomain(AddCompanyRequest request) {
        Company company = new Company();

        company.setName(request.name);
        company.setEmail(request.email);
        company.setDescription(request.description);
        company.setStatus(CompanyStatus.active);
        // TODO: location

        return company;
    }

    public static CompanyDto toDto(Company company) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.id = company.getId();
        companyDto.companyName = company.getName();
        companyDto.email = company.getEmail();
        companyDto.description = company.getDescription();
        // TODO: location

        return companyDto;
    }
}
