package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

     boolean addCompany(Company company);

     Company getCompany(int companyId);

     boolean editCompany(Company company);

     boolean deleteCompany(int companyId);
}
