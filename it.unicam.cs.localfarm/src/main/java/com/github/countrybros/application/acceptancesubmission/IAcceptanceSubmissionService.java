package com.github.countrybros.application.acceptancesubmission;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.web.acceptancesubmission.request.AcceptanceSubmissionRequest;

import java.util.List;


/**
 * Interface that represents every possible implementation of AcceptanceSubmissionService
 */
public interface IAcceptanceSubmissionService {

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param request the submission to add.
     */
     void addAcceptanceSubmission(AcceptanceSubmissionRequest request);

    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     */
     void deleteAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
     AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
     List<AcceptanceSubmission> getAvailableAcceptanceSubmissions();

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
     List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId);

     //TODO: Add acceptance submission by sender.

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    void onAcceptance(int submissionId);

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    void onRefusal(int submissionId);

    /**
     * Assigns the review on a curator.
     *
     * @param submissionId The sub to assign.
     * @param userId the curator that takes care of the sub
     */
    void takeChargeOfSubmission(int submissionId, int userId);
}
