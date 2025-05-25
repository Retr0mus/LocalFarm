package com.github.countrybros.application;

import com.github.countrybros.model.Company;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the companies.
 */
public class CompanyService {
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