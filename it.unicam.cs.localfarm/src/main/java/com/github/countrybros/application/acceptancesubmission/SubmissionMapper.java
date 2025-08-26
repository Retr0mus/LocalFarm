package com.github.countrybros.application.acceptancesubmission;

import com.github.countrybros.model.acceptancesubmission.AddProductSubmission;
import com.github.countrybros.model.acceptancesubmission.RecogniseProductSubmission;
import com.github.countrybros.web.acceptancesubmission.request.AddProductSubmissionRequest;
import com.github.countrybros.web.acceptancesubmission.request.RecogniseProductSubmissionRequest;

public class SubmissionMapper {

    public static RecogniseProductSubmission toDoamin(RecogniseProductSubmissionRequest submissionRequest) {
         return new RecogniseProductSubmission(submissionRequest.getSenderId(), submissionRequest.getProductId(), submissionRequest.getQta());
    }

    public static AddProductSubmission toDomain(AddProductSubmissionRequest submissionRequest) {
        //TODO Implement
        return null;
    }
}
