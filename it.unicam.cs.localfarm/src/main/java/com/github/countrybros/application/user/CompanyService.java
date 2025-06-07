package com.github.countrybros.application.user;

import com.github.countrybros.infrastructure.repository.ICompanyRepository;
import com.github.countrybros.model.user.Company;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the companies.
 */
@Service
public class CompanyService implements ICompanyService {
    private ICompanyRepository companyRepository;

    public void addCompany(Company company) {

    }

    public Company getCompany(int companyId) {
            return companyRepository.getCompaniesById(companyId);
    }

    public void editCompany(Company company) {

    }

    public void deleteCompany(int companyId) {

    }


}