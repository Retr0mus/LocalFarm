package com.github.countrybros.web.user;

import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.user.Company;
import com.github.countrybros.web.user.request.AddComapanyRequest;
import com.github.countrybros.web.user.request.EditCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody AddComapanyRequest request) {
        companyService.addCompany(request);
        return new ResponseEntity<>("Company added", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Company> getCompany(@RequestParam int companyId) {
        Company company = companyService.getCompany(companyId);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> editCompany(@RequestBody EditCompanyRequest request) {
        companyService.editCompany(request);
        return new ResponseEntity<>("Company updated", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany(@RequestParam int companyId) {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>("Company deleted", HttpStatus.OK);
    }


}
