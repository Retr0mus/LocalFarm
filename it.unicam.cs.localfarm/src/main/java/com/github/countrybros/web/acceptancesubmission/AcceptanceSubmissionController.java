package com.github.countrybros.web.acceptancesubmission;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.web.acceptancesubmission.request.AcceptanceSubmissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class AcceptanceSubmissionController {


    private final IAcceptanceSubmissionService acceptanceSubmissionService;

    @Autowired
    public AcceptanceSubmissionController(IAcceptanceSubmissionService acceptanceSubmissionService) {
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<AcceptanceSubmission>> getAvailable() {
        List<AcceptanceSubmission> submissions = acceptanceSubmissionService.getAvailableAcceptanceSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/curator")
    public ResponseEntity<List<AcceptanceSubmission>> getByCurator(@RequestParam int id) {
        List<AcceptanceSubmission> submissions = acceptanceSubmissionService.getAcceptanceSubmissionsByCurator(id);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/acceptancesubmission")
    public ResponseEntity<?> getAcceptanceSubmission(@RequestParam int submissionId) {
        AcceptanceSubmission submission = acceptanceSubmissionService.getAcceptanceSubmission(submissionId);
        if (submission == null) {
            return new ResponseEntity<>("Submission not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<String> onAcceptance(@RequestParam int submissionId, @RequestParam int curatorId) {
        boolean success = acceptanceSubmissionService.onAcceptance(submissionId,curatorId);
        if (success) {
            return new ResponseEntity<>("Submission accepted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Acceptance failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/refuse")
    public ResponseEntity<String> onRefusal(@RequestParam int submissionId) {
        boolean success = acceptanceSubmissionService.onRefusal(submissionId);
        if (success) {
            return new ResponseEntity<>("Submission refused", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Refusal failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAcceptanceSubmission(@RequestBody AcceptanceSubmissionRequest request) {
        acceptanceSubmissionService.addAcceptanceSubmission(request);
        return new ResponseEntity<>("Acceptance submission added", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAcceptanceSubmission(@RequestParam int acceptanceSubmissionId) {
        acceptanceSubmissionService.deleteAcceptanceSubmission(acceptanceSubmissionId);
        return new ResponseEntity<>("Acceptance submission deleted", HttpStatus.OK);
    }


}
