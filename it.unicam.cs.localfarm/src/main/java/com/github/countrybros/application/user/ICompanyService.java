package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;
import org.springframework.stereotype.Service;

/**
 * Defines the responabiliti of management of companies.
 */
@Service
public interface ICompanyService {

    public boolean addCompany(Company company);

    public Company getCompany(int companyId);

    public boolean editCompany(Company company);

    public boolean deleteCompany(int companyId);
}
