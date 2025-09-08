package com.github.countrybros.web.controllers.submission;

import com.github.countrybros.application.facades.Orchestrator;
import com.github.countrybros.application.mappers.SubmissionMapper;
import com.github.countrybros.application.models.dtos.submission.SubmissionDto;
import com.github.countrybros.application.services.submission.ISubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.models.requests.submission.RecogniseProductSubmissionRequest;
import jakarta.validation.Valid;
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
    private final ISubmissionService submissionService;

    @Autowired
    public SubmissionController(Orchestrator orchestrator,
                                ISubmissionService submissionService) {
        this.submissionService = submissionService;
        this.orchestrator = orchestrator;

    }

    @GetMapping("get")
    public ResponseEntity<Object> getSubmission(@PathParam("submissionId") int submissionId) {

        return new ResponseEntity<>(submissionService.getSubmission(submissionId), HttpStatus.OK);
    }

    @GetMapping("SubmissionToReview")
    public ResponseEntity<Object> getSubmissionToReview(@PathParam("curatorId") int curatorId) {

        return new ResponseEntity<>(SubmissionMapper.toDTO(submissionService
                .getSubmissionToReview(curatorId)), HttpStatus.OK);
    }

    @PutMapping("addQuantityToStock")
    public ResponseEntity<Object> addItemQuantity(@Valid @RequestBody RecogniseProductSubmissionRequest request) throws ImpossibleRequestException {
        try {
            orchestrator.addSubmissionQuantityToStock(request);
        } catch (ImpossibleRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("A submission to recognize this stock's quantity has been created successfully", HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SubmissionDto>> getAvailable() {

        List<SubmissionDto> submissions = SubmissionMapper.toDTO(submissionService.getAvailableSubmissions());
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @PutMapping("/accept")
    public ResponseEntity<Object> acceptSubmission(
            @RequestParam int id, @RequestParam boolean accepted) {

        orchestrator.acceptSubmission(id, accepted);
        return new ResponseEntity<>("Submission successfully updated", HttpStatus.OK);
    }

    @PutMapping("takeCharge")
    public ResponseEntity<String> takeChargeOfSubmission(@RequestParam("userId") int userId,@RequestParam("subId") int submissionId) {
        orchestrator.takeChargeOfSubmission(userId,submissionId);
        return new ResponseEntity<>("submission taken", HttpStatus.OK);
    }
}
