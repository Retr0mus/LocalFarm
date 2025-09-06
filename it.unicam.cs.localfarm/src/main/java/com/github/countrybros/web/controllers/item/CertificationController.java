package com.github.countrybros.web.controllers.item;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.models.requests.item.AddCertificationRequest;
import com.github.countrybros.application.services.item.CertificationService;
import com.github.countrybros.application.services.item.ICertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for management of ItemDetails
 */
@RestController
@RequestMapping( "/certifications")
public class CertificationController {

    private final ICertificationService certificationService;

    @Autowired
    public CertificationController(ICertificationService certificationService) {

        this.certificationService = certificationService;
    }

    @PostMapping("addRequest")
    public ResponseEntity<Object> addCertification(@RequestBody AddCertificationRequest request) {

        certificationService.addCertification(request);
        return new ResponseEntity<>("Certification successfully created", HttpStatus.OK);
    }
}