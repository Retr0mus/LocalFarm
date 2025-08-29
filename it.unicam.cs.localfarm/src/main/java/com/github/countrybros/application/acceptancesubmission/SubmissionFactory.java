package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.model.acceptancesubmission.*;
import com.github.countrybros.web.acceptancesubmission.request.*;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class SubmissionFactory {

    /*public Submission create(SubmissionRequest request) {
        Submission submission;

        //TODO tutta sta roba va cambiata, è assolutamente disgustoso.
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

            case "editProduct" -> {
                EditProductSubmissionRequest editReq = (EditProductSubmissionRequest) request;
                EditProductSubmission editSubmission = new EditProductSubmission();
                editSubmission.setProductToEditId(editReq.getProductToEditId());
                editSubmission.setProductChangeId(editReq.getProductChangeId());
                submission = editSubmission;
            }

            case "recogniseProduct" -> {
                RecogniseProductSubmissionRequest recReq = (RecogniseProductSubmissionRequest) request;
                RecogniseProductSubmission recSubmission = new RecogniseProductSubmission();
                recSubmission.setItemId(recReq.getProductId());
                recSubmission.setQta(recReq.getQta());
                submission = recSubmission;
            }

            case "removeProduct" -> {
                RemoveProductSubmissionRequest remReq = (RemoveProductSubmissionRequest) request;
                RemoveProductSubmission remSubmission = new RemoveProductSubmission();
                remSubmission.setProductId(remReq.getProductId());
                submission = remSubmission;
            }

            default -> throw new IllegalArgumentException("Unsupported submission type: " + request.getType());
        }

        submission.setAccepted(false);
        submission.setSenderId(request.getSenderId());

        return submission;
    }*/
}




