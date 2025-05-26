package com.github.countrybros.web;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class AcceptanceSubmissionController {
    private IAcceptanceSubmissionService acceptanceSubmissionService;

    @Autowired
    public AcceptanceSubmissionController(IAcceptanceSubmissionService service) {
        this.acceptanceSubmissionService = service;
    }

    @GetMapping("/{submissionId}")
    public AcceptanceSubmission getAcceptanceSubmission(int submissionId) {
        return acceptanceSubmissionService.getAcceptanceSubmission(submissionId);
    }

    @PostMapping("/{submissionId}/accept")
    public boolean onAcceptance(int submissionId) {
        return acceptanceSubmissionService.onAcceptance(submissionId);
    }

    @PostMapping("/{submissionId}/refuse")
    public boolean onRefusal(int submissionId) {
        return acceptanceSubmissionService.onRefusal(submissionId);
    }
}
