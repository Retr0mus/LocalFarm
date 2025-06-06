package com.github.countrybros.application.user;

import com.github.countrybros.model.user.Company;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the companies.
 */
@Service
public class CompanyService implements ICompanyService {

    private Map<Integer, Company> companies = new HashMap<>();

    public boolean addCompany(Company company) {
        return false;
    }

    public Company getCompany(int companyId) {
        return null;
    }

    public boolean editCompany(Company company) {
        return false;
    }

    public boolean deleteCompany(int companyId) {
        return false;
    }


}