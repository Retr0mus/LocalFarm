package com.github.countrybros.web;

import com.github.countrybros.application.IAcceptanceSubmissionService;
import com.github.countrybros.model.AcceptanceSubmission;

public class AcceptanceSubmissionController {
    private IAcceptanceSubmissionService acceptanceSubmissionService;

    public AcceptanceSubmissionController(IAcceptanceSubmissionService service) {
        this.acceptanceSubmissionService = service;
    }

    public AcceptanceSubmission getAcceptanceSubmission(int submissionId) {
        return acceptanceSubmissionService.getAcceptanceSubmission(submissionId);
    }

    public boolean onAcceptance(int submissionId) {
        return acceptanceSubmissionService.onAcceptance(submissionId);
    }

    public boolean onRefusal(int submissionId) {
        return acceptanceSubmissionService.onRefusal(submissionId);
    }
}
