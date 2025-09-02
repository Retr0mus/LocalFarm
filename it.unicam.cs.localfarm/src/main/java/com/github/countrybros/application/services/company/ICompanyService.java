package com.github.countrybros.application.services.company;

import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.models.requests.company.AddComapanyRequest;
import com.github.countrybros.application.models.requests.company.EditCompanyRequest;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

     void addCompany(AddComapanyRequest request);

     Company getCompany(int companyId);

     void editCompany(EditCompanyRequest request);

     void deleteCompany(int companyId);
}
