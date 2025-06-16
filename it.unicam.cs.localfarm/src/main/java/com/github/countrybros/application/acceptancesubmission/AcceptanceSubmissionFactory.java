package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.model.acceptancesubmission.*;
import com.github.countrybros.web.acceptancesubmission.request.*;
import org.springframework.stereotype.Component;

@Component
public class AcceptanceSubmissionFactory {

    public AcceptanceSubmission create(AcceptanceSubmissionRequest request) {
        AcceptanceSubmission submission;

        //TODO recognise è la richiesta di un aumento della quantita di un item
        //Crea o aumente la quantita di un item a partire da un prodotto, company e qta

        if (request.getType() == null) {
            throw new IllegalArgumentException("Submission type must not be null");
        }
        switch (request.getType()) {
            case "addProduct" -> {
                AddProductAcceptanceSubmissionRequest addReq = (AddProductAcceptanceSubmissionRequest) request;
                AddProductAcceptanceSubmission addSubmission = new AddProductAcceptanceSubmission();
                addSubmission.setItemDetailsId(addReq.getItemDetailsId());
                submission = addSubmission;
            }

            case "editProduct" -> {
                EditProductAcceptanceSubmissionRequest editReq = (EditProductAcceptanceSubmissionRequest) request;
                EditProductAcceptanceSubmission editSubmission = new EditProductAcceptanceSubmission();
                editSubmission.setProductToEditId(editReq.getProductToEditId());
                editSubmission.setProductChangeId(editReq.getProductChangeId());
                submission = editSubmission;
            }

            case "recogniseProduct" -> {
                RecogniseProductAcceptanceSubmissionRequest recReq = (RecogniseProductAcceptanceSubmissionRequest) request;
                RecogniseProductAcceptanceSubmission recSubmission = new RecogniseProductAcceptanceSubmission();
                recSubmission.setProductId(recReq.getProductId());
                recSubmission.setQta(recReq.getQta());
                submission = recSubmission;
            }

            case "removeProduct" -> {
                RemoveProductAcceptanceSubmissionRequest remReq = (RemoveProductAcceptanceSubmissionRequest) request;
                RemoveProductAcceptanceSubmission remSubmission = new RemoveProductAcceptanceSubmission();
                remSubmission.setProductId(remReq.getProductId());
                submission = remSubmission;
            }

            default -> throw new IllegalArgumentException("Unsupported submission type: " + request.getType());
        }

        submission.setAccepted(false);
        submission.setSenderId(request.getSenderId());

        return submission;
    }
}




