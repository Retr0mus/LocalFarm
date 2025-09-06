package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.company.CompanyDTO;
import com.github.countrybros.model.company.Company;

public class CompanyMapper {

    /**
     * Converte un'entità Company in un DTO.
     * Qui vengono mappati solo i campi essenziali per evitare lazy fetch di relazioni.
     */
    public static CompanyDTO toDTO(Company company) {
        if (company == null) return null;

        CompanyDTO dto = new CompanyDTO();
        dto.id = company.getId();
        dto.companyName = company.getName();
        dto.description = company.getDescription();
        dto.email = company.getEmail();
        return dto;
    }

    /**
     * Converte un DTO in entità Company.
     * Utile se vuoi ricreare o aggiornare un'entità a partire da un DTO.
     */
    public static Company toEntity(CompanyDTO dto) {
        if (dto == null) return null;

        Company company = new Company();
        company.setName(dto.companyName);
        company.setDescription(dto.description);
        company.setEmail(dto.email);
        return company;
    }
}