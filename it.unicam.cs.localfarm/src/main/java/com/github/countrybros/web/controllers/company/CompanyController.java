package com.github.countrybros.web.controllers.company;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.CompanyMapper;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.models.requests.company.AddCompanyRequest;
import com.github.countrybros.application.models.requests.company.EditCompanyRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private Orchestrator orchestrator;

    @PostMapping("/add")
    public ResponseEntity<String> addCompany(@RequestBody AddCompanyRequest request) {
        companyService.addCompany(request);
        return new ResponseEntity<>("Company added", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getCompany(@PathParam("companyId") int companyId) {
        return new ResponseEntity<>(CompanyMapper.toDto(companyService.getCompany(companyId)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies().stream().map(CompanyMapper::toDto), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editCompany(@RequestBody EditCompanyRequest request) {
        companyService.editCompany(request);
        return new ResponseEntity<>("Company updated", HttpStatus.OK);
    }

    @DeleteMapping("/disable")
    public ResponseEntity<String> disableCompany(@PathParam("companyId") int companyId,
                                                 @PathParam("adminId") int adminId) {
        orchestrator.disableCompany(companyId, adminId);
        return new ResponseEntity<>("Company deleted", HttpStatus.OK);
    }
}
