package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.model.acceptancesubmission.*;
import com.github.countrybros.web.acceptancesubmission.request.*;
import org.springframework.stereotype.Component;

@Component
public class SubmissionFactory {

    public Submission create(SubmissionRequest request) {
        Submission submission;

        //TODO recognise è la richiesta di un aumento della quantita di un item
        //Crea o aumente la quantita di un item a partire da un prodotto, company e qta

        if (request.getType() == null) {
            throw new IllegalArgumentException("Submission type must not be null");
        }
        switch (request.getType()) {
            case "addProduct" -> {
                AddProductSubmissionRequest addReq = (AddProductSubmissionRequest) request;
                AddProductSubmission addSubmission = new AddProductSubmission();
                addSubmission.setItemDetailsId(addReq.getItemDetailsId());
                submission = addSubmission;
            }
            case "recogniseProduct" -> {
                RecogniseProductSubmissionRequest recReq = (RecogniseProductSubmissionRequest) request;
                RecogniseProductSubmission recSubmission = new RecogniseProductSubmission();
                recSubmission.setProductId(recReq.getProductId());
                recSubmission.setQta(recReq.getQta());
                submission = recSubmission;
            }

            default -> throw new IllegalArgumentException("Unsupported submission type: " + request.getType());
        }

        submission.setStatus(SubmissionStatus.pending);
        submission.setSenderId(request.getSenderId());

        return submission;
    }
}




