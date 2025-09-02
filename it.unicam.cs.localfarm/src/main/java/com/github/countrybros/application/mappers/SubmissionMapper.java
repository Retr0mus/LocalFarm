package com.github.countrybros.application.mappers;

import com.github.countrybros.model.submission.AddProductSubmission;
import com.github.countrybros.model.submission.RecogniseProductSubmission;
import com.github.countrybros.application.models.requests.submission.AddProductSubmissionRequest;
import com.github.countrybros.application.models.requests.submission.RecogniseProductSubmissionRequest;

public class SubmissionMapper {

    public static RecogniseProductSubmission toDomain(RecogniseProductSubmissionRequest submissionRequest) {
         return new RecogniseProductSubmission(submissionRequest.getSenderId(), submissionRequest.getProductId(), submissionRequest.getQta());
    }

    public static AddProductSubmission toDomain(AddProductSubmissionRequest submissionRequest) {
        return new AddProductSubmission(submissionRequest.getSenderId(), submissionRequest.getItemId());
    }
}
