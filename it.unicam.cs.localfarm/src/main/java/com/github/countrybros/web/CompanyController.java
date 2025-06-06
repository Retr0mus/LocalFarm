package com.github.countrybros.web;

import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.user.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @PostMapping
    public boolean addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping
    public Company getCompany(@RequestParam int companyId) {
        return companyService.getCompany(companyId);
    }

    @PutMapping
    public boolean editCompany(@RequestParam Company company) {
        return companyService.editCompany(company);
    }

    @DeleteMapping
    public boolean deleteCompany(@RequestParam int companyId) {
        return companyService.deleteCompany(companyId);
    }


}
