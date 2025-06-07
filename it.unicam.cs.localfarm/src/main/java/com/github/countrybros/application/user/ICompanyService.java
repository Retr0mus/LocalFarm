package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

     void addCompany(Company company);

     Company getCompany(int companyId);

     void editCompany(Company company);

     void deleteCompany(int companyId);
}
