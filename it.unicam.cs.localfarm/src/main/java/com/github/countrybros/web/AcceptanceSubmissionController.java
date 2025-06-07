package com.github.countrybros.web;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.AddProductAcceptanceSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class AcceptanceSubmissionController {

    @Autowired
    private final IAcceptanceSubmissionService acceptanceSubmissionService;

    @Autowired
    public AcceptanceSubmissionController(IAcceptanceSubmissionService acceptanceSubmissionService) {
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }

    @GetMapping("/available")
    public List<AcceptanceSubmission> getAvailable() {
        return acceptanceSubmissionService.getAvailableAcceptanceSubmissions();
    }

    @GetMapping("/curator")
    public List<AcceptanceSubmission> getByCurator(@RequestParam int id) {
        return acceptanceSubmissionService.getAcceptanceSubmissionsByCurator(id);
    }

    @GetMapping("/acceptance-submission")
    public AcceptanceSubmission getAcceptanceSubmission(@RequestParam int submissionId) {
        return acceptanceSubmissionService.getAcceptanceSubmission(submissionId);
    }

    @PostMapping("/accept")
    public void onAcceptance( @RequestParam int submissionId) {

    }

    @DeleteMapping("/refuse")
    public void onRefusal(@RequestParam int submissionId) {

    }

    @PostMapping("/add")
    public void addAcceptanceSubmission(@RequestBody AcceptanceSubmission acceptanceSubmission) {
        acceptanceSubmissionService.addAcceptanceSubmission(acceptanceSubmission);
    }

    @DeleteMapping("/delete")
    public void deleteAcceptanceSubmission(@RequestParam int acceptanceSubmissionId) {
        acceptanceSubmissionService.deleteAcceptanceSubmission(acceptanceSubmissionId);
    }


}
