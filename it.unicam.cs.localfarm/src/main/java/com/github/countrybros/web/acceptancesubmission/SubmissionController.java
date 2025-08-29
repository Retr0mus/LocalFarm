package com.github.countrybros.web.acceptancesubmission;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.web.acceptancesubmission.request.RecogniseProductSubmissionRequest;
import com.github.countrybros.web.acceptancesubmission.request.SubmissionRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final Orchestrator orchestrator;

    @Autowired
    public SubmissionController(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PutMapping( "addQuantityToStock")
    public ResponseEntity<Object> addItemQuantity(@RequestBody RecogniseProductSubmissionRequest request) throws ImpossibleRequestException {
        try {
            orchestrator.addQuantityToStock(request);
        } catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("A submission to recognize this stock's quantity has been created successfully", HttpStatus.OK);
    }

    /*@GetMapping("/available")
    public ResponseEntity<List<Submission>> getAvailable() {

        List<Submission> submissions = acceptanceSubmissionService.getAvailableAcceptanceSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/curator")
    public ResponseEntity<List<Submission>> getByCurator(@RequestParam int id) {

        List<Submission> submissions = acceptanceSubmissionService.getAcceptanceSubmissionsByCurator(id);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/acceptancesubmission")
    public ResponseEntity<?> getAcceptanceSubmission(@RequestParam int submissionId) {

        return new ResponseEntity<>(acceptanceSubmissionService.getSubmission(submissionId),
                HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<String> onAcceptance(@RequestParam int submissionId) {

        acceptanceSubmissionService.onAcception(submissionId);
        return new ResponseEntity<>("Submission accepted", HttpStatus.OK);
    }

    @DeleteMapping("/refuse")
    public ResponseEntity<String> onRefusal(@RequestParam int submissionId) {

        acceptanceSubmissionService.onRejection(submissionId);
        return new ResponseEntity<>("Submission refused", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAcceptanceSubmission(@RequestParam int acceptanceSubmissionId) {
        acceptanceSubmissionService.deleteSubmission(acceptanceSubmissionId);
        return new ResponseEntity<>("Acceptance submission deleted", HttpStatus.OK);
    }


    @PutMapping("takeCharge")
    public ResponseEntity<String> takeCharge(@PathParam("subId") int acceptanceSubId,
                                             @PathParam("userId") int userId) {
        acceptanceSubmissionService.takeChargeOfSubmission(acceptanceSubId, userId);
        return new ResponseEntity<>("Acceptance submission taken", HttpStatus.OK);
    }*/
}
