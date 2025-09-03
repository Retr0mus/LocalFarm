package com.github.countrybros.application.services.company;

import com.github.countrybros.application.abstractions.IEmailService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.mappers.CompanyMapper;
import com.github.countrybros.infrastructure.repositories.user.ICompanyRepository;
import com.github.countrybros.infrastructure.services.email.MockEmailService;
import com.github.countrybros.model.company.CompanyStatus;
import com.github.countrybros.model.utils.Email;
import com.github.countrybros.model.company.Company;
import com.github.countrybros.application.models.requests.company.AddCompanyRequest;
import com.github.countrybros.application.models.requests.company.EditCompanyRequest;
import com.github.countrybros.model.utils.PasswordSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the companies.
 */
@Service
public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final IEmailService emailService;

    @Autowired
    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.emailService = new MockEmailService();
    }

    public void addCompany(AddCompanyRequest request) {

        if(companyRepository.existsByEmailEqualsIgnoreCase(request.email))
            throw new ImpossibleRequestException("Email already registered");

        Company company = CompanyMapper.toDomain(request);
        String password = PasswordSuite.generateRandomPassword(20);
        company.setPassword(PasswordSuite.hashPassword(password));

        companyRepository.save(company);

        Email email = new Email();
        email.setSender("noreply@localfarm.it");
        email.setRecipient(company.getEmail());
        email.setSubject("Company Registration");
        email.setBody("Your company has been registered, login with this email and password: " + password);

        emailService.sendEmail(email);
    }

    @Override
    public Company getCompany(int companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Company with ID " + companyId + " not found."));
    }

    @Override
    public List<Company> getAllCompanies() {
        return (List<Company>) companyRepository.findAll();
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
    public void disableCompany(int companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundInRepositoryException("Company with ID " + companyId + " not found.");
        }

        Company company = getCompany(companyId);

        if(company.getStatus() == CompanyStatus.inactive)
            throw new ImpossibleRequestException("Company with ID " + companyId + " is already disabled.");

        company.setStatus(CompanyStatus.inactive);
        companyRepository.save(company);

        Email email = new Email();
        email.setSender("noreply@localfarm.it");
        email.setRecipient(company.getEmail());
        email.setSubject("Company disabled from LocalFarm");
        email.setBody("Your company has been disabled.");

        emailService.sendEmail(email);
    }
}