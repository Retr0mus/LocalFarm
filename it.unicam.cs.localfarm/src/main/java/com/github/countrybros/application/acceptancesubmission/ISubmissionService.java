package com.github.countrybros.application.acceptancesubmission;

import com.github.countrybros.model.acceptancesubmission.AddProductSubmission;
import com.github.countrybros.model.acceptancesubmission.Submission;
import com.github.countrybros.web.acceptancesubmission.request.AddProductSubmissionRequest;
import com.github.countrybros.web.acceptancesubmission.request.SubmissionRequest;

import java.util.List;


/**
 * Interface that represents every possible implementation of SubmissionService
 */
public interface ISubmissionService {

    /**
     * Adds an Submission.
     *
     * @param request the submission to add.
     */
     void addSubmission(Submission request);

    /**
     * Deletes an Submission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     */
     void deleteSubmission(int acceptanceSubmissionId);

    /**
     * Gets the required Submission.
     *
     * @param SubmissionId the Id of the wanted Submission.
     * @return the said AcceptanceSubmission.
     */
     Submission getSubmission(int SubmissionId);

    /**
     * Gets all the free Submission.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
     List<Submission> getAvailableAcceptanceSubmissions();

    /**
     * Gets all the Submission assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
     List<Submission> getAcceptanceSubmissionsByCurator(int curatorId);

     //TODO: Add acceptance submission by sender.

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    void onAcception(int submissionId);

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    void onRejection(int submissionId);

    /**
     * Assigns the review on a curator.
     *
     * @param submissionId The sub to assign.
     * @param userId the curator that takes care of the sub.
     */
    void takeChargeOfSubmission(int submissionId, int userId);
}
