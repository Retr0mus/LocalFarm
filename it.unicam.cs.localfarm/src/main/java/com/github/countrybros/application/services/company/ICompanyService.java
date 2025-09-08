package com.github.countrybros.application.services.company;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.models.requests.company.AddCompanyRequest;
import com.github.countrybros.application.models.requests.company.EditCompanyRequest;

import java.util.List;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

     void addCompany(AddCompanyRequest request);

     Company getCompany(int companyId);

     List<Company> getAllCompanies();

     void editCompany(EditCompanyRequest request);

     void disableCompany(int companyId);
}
