package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.user.request.AddComapanyRequest;
import com.github.countrybros.web.user.request.EditCompanyRequest;

/**
 * Defines the responabiliti of management of companies.
 */
public interface ICompanyService {

     void addCompany(AddComapanyRequest request);

     Company getCompany(int companyId);

     void editCompany(EditCompanyRequest request);

     void deleteCompany(int companyId);
}
