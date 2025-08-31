package com.github.countrybros.web.acceptancesubmission;

import com.github.countrybros.application.Orchestrator;
import com.github.countrybros.application.acceptancesubmission.ISubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.model.acceptancesubmission.Submission;
import com.github.countrybros.web.acceptancesubmission.request.RecogniseProductSubmissionRequest;
import com.github.countrybros.web.acceptancesubmission.request.SubmissionRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final Orchestrator orchestrator;
    private final ISubmissionService acceptanceSubmissionService;

    @Autowired
    public SubmissionController(Orchestrator orchestrator,
                                ISubmissionService acceptanceSubmissionService) {

        this.orchestrator = orchestrator;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
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

    @GetMapping("/available")
    public ResponseEntity<List<Submission>> getAvailable() {

        List<Submission> submissions = orchestrator.getAvailableSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/accept")
    public ResponseEntity<Object> acceptSubmission(
            @RequestParam int id, @RequestParam boolean accepted) {

        orchestrator.acceptSubmission(id, accepted);
        return new ResponseEntity<>("Submission successfully updated", HttpStatus.OK);
    }
}


    /*@GetMapping("/available")
    public ResponseEntity<List<Submission>> getAvailable() {

        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }


    }

    public ResponseEntity<?> getAcceptanceSubmission(@RequestParam int submissionId) {

                HttpStatus.OK);
    }

