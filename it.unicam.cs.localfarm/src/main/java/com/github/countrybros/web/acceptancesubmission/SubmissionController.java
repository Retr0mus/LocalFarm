package com.github.countrybros.web.acceptancesubmission;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.model.acceptancesubmission.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final ISubmissionService acceptanceSubmissionService;
    private final Orchestrator orchestrator;

    @Autowired
    public SubmissionController(ISubmissionService acceptanceSubmissionService,
                                IItemService itemDetailsService, Orchestrator orchestrator) {

        this.acceptanceSubmissionService = acceptanceSubmissionService;
        this.orchestrator = orchestrator;
    }

    @GetMapping("/available")
    public ResponseEntity<List<Submission>> getAvailable() {

        List<Submission> submissions = acceptanceSubmissionService.getAvailableAcceptanceSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

//    @GetMapping("/curator")
//    public ResponseEntity<List<AcceptanceSubmission>> getByCurator(@RequestParam int id) {
//
//        List<AcceptanceSubmission> submissions = acceptanceSubmissionService.getAcceptanceSubmissionsByCurator(id);
//        return new ResponseEntity<>(submissions, HttpStatus.OK);
//    }

    @GetMapping("/acceptancesubmission")
    public ResponseEntity<?> getAcceptanceSubmission(@RequestParam int submissionId) {

        return new ResponseEntity<>(acceptanceSubmissionService.getAcceptanceSubmission(submissionId),
                HttpStatus.OK);
    }

//    @PostMapping("/accept")
//    public ResponseEntity<String> onAcceptance(@RequestParam int submissionId) {
//
//        acceptanceSubmissionService.onAcceptance(submissionId);
//        return new ResponseEntity<>("Submission accepted", HttpStatus.OK);
//    }

//    @DeleteMapping("/refuse")
//    public ResponseEntity<String> onRefusal(@RequestParam int submissionId) {
//
//        acceptanceSubmissionService.onRefusal(submissionId);
//        return new ResponseEntity<>("Submission refused", HttpStatus.OK);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addAcceptanceSubmission(@RequestBody SubmissionRequest request) {
//
//        acceptanceSubmissionService.addAcceptanceSubmission(request);
//        return new ResponseEntity<>("Acceptance submission added", HttpStatus.CREATED);
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteAcceptanceSubmission(@RequestParam int acceptanceSubmissionId) {
//        acceptanceSubmissionService.deleteAcceptanceSubmission(acceptanceSubmissionId);
//        return new ResponseEntity<>("Acceptance submission deleted", HttpStatus.OK);
//    }


    @PutMapping("takeCharge")
    public ResponseEntity<String> takeChargeOfSubmission(@RequestParam("userId") int userId,@RequestParam("subId") int submissionId) {
        orchestrator.takeChargeOfSubmission(userId,submissionId);
        return new ResponseEntity<>("Acceptance submission taken", HttpStatus.OK);
    }
}
