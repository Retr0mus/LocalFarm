package com.github.countrybros.web;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submissions")
public class AcceptanceSubmissionController {
    private final IAcceptanceSubmissionService acceptanceSubmissionService;

    @Autowired
    public AcceptanceSubmissionController(IAcceptanceSubmissionService acceptanceSubmissionService) {
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }

    @GetMapping("/{submissionId}")
    public AcceptanceSubmission getAcceptanceSubmission(@RequestParam int submissionId) {
        return acceptanceSubmissionService.getAcceptanceSubmission(submissionId);
    }

    @PostMapping("/{submissionId}/accept")
    public boolean onAcceptance( @RequestParam int submissionId) {
        return acceptanceSubmissionService.onAcceptance(submissionId);
    }

    @PostMapping("/{submissionId}/refuse")
    public boolean onRefusal(@RequestParam int submissionId) {
        return acceptanceSubmissionService.onRefusal(submissionId);
    }


}
