package com.github.countrybros.application;

import com.github.countrybros.model.Company;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

    public boolean addCompany(Company company);

    public Company getCompany(int companyId);

    public boolean editCompany(Company company);

    public boolean deleteCompany(int companyId);
}
