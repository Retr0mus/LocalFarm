package com.github.countrybros.web.user;

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
    public void addCompany(@RequestBody Company company) {
         companyService.addCompany(company);
    }

    @GetMapping
    public Company getCompany(@RequestParam int companyId) {
         return companyService.getCompany(companyId);
    }

    @PutMapping
    public void editCompany(@RequestParam Company company) {
         companyService.editCompany(company);
    }

    @DeleteMapping
    public void deleteCompany(@RequestParam int companyId) {
         companyService.deleteCompany(companyId);
    }


}
