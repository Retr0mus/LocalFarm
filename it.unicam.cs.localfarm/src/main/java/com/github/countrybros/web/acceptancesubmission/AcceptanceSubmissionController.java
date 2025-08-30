package com.github.countrybros.web.acceptancesubmission;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.application.product.IItemService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.web.acceptancesubmission.request.AcceptanceSubmissionRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class AcceptanceSubmissionController {

    private final Orchestrator orchestrator;
    private final IAcceptanceSubmissionService acceptanceSubmissionService;

    @Autowired
    public AcceptanceSubmissionController(Orchestrator orchestrator,
                                          IAcceptanceSubmissionService acceptanceSubmissionService) {

        this.orchestrator = orchestrator;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<AcceptanceSubmission>> getAvailable() {

        List<AcceptanceSubmission> submissions = orchestrator.getAvailableSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/accept")
    public ResponseEntity<Object> acceptSubmission(
            @RequestParam int id, @RequestParam boolean accepted) {

        orchestrator.acceptSubmission(id, accepted);
        return new ResponseEntity<>("Submission successfully updated", HttpStatus.OK);
    }

//    @GetMapping("/curator")
//    public ResponseEntity<List<AcceptanceSubmission>> getByCurator(@RequestParam int id) {
//
//        List<AcceptanceSubmission> submissions = acceptanceSubmissionService.getAcceptanceSubmissionsByCurator(id);
//        return new ResponseEntity<>(submissions, HttpStatus.OK);
//    }
//
    @GetMapping("/submission")
    public ResponseEntity<?> getAcceptanceSubmission(@RequestParam int submissionId) {

        return new ResponseEntity<>(acceptanceSubmissionService.getAcceptanceSubmission(submissionId),
                HttpStatus.OK);
    }
//
//    @PostMapping("/accept")
//    public ResponseEntity<String> onAcceptance(@RequestParam int submissionId) {
//
//        acceptanceSubmissionService.onAcceptance(submissionId);
//        return new ResponseEntity<>("Submission accepted", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/refuse")
//    public ResponseEntity<String> onRefusal(@RequestParam int submissionId) {
//
//        acceptanceSubmissionService.onRefusal(submissionId);
//        return new ResponseEntity<>("Submission refused", HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addAcceptanceSubmission(@RequestBody AcceptanceSubmissionRequest request) {
//
//        acceptanceSubmissionService.addAcceptanceSubmission(request);
//        return new ResponseEntity<>("Acceptance submission added", HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteAcceptanceSubmission(@RequestParam int acceptanceSubmissionId) {
//        acceptanceSubmissionService.deleteAcceptanceSubmission(acceptanceSubmissionId);
//        return new ResponseEntity<>("Acceptance submission deleted", HttpStatus.OK);
//    }
//
//
//    @PutMapping("takeCharge")
//    public ResponseEntity<String> takeCharge(@PathParam("subId") int acceptanceSubId,
//                                             @PathParam("userId") int userId) {
//        acceptanceSubmissionService.takeChargeOfSubmission(acceptanceSubId, userId);
//        return new ResponseEntity<>("Acceptance submission taken", HttpStatus.OK);
//    }
}
