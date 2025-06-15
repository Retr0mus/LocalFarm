package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.ICompanyRepository;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.user.request.AddComapanyRequest;
import com.github.countrybros.web.user.request.EditCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the companies.
 */
@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository companyRepository;

    public void addCompany(AddComapanyRequest request) {
        Company company = new Company();
        company.setName(request.name);
        company.setEmail(request.email);
        company.setPassword(request.password);
        company.setDescription(request.description);
        //TODO: location

        companyRepository.save(company);

    }

    @Override
    public Company getCompany(int companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Company with ID " + companyId + " not found."));
    }

    @Override
    public void editCompany(EditCompanyRequest request) {
        Company company = companyRepository.findById(request.id)
                .orElseThrow(() -> new NotFoundInRepositoryException("Company with ID " + request.id + " not found."));

        company.setName(request.name);
        company.setEmail(request.email);
        company.setPassword(request.password);
        company.setDescription(request.description);

        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(int companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundInRepositoryException("Company with ID " + companyId + " not found.");
        }
        companyRepository.deleteById(companyId);
    }




}