package com.github.countrybros.web.controllers.item;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.models.requests.item.AddCertificationRequest;
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

    private final Orchestrator orchestrator;

    @Autowired
    public CertificationController(Orchestrator orchestrator) {

        this.orchestrator = orchestrator;
    }

    @PostMapping("addRequest")
    public ResponseEntity<Object> addCertification(@RequestBody AddCertificationRequest request) {

        orchestrator.addCertification(request);
        return new ResponseEntity<>("Certification successfully created", HttpStatus.OK);
    }
}