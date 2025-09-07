package com.github.countrybros.application.services.submission;

import com.github.countrybros.model.submission.Submission;
import com.github.countrybros.model.user.User;

import java.util.List;


/**
 * Interface that represents every possible implementation of SubmissionService
 */
public interface ISubmissionService {

    /**
     * Adds an Submission.
     *
     * @param submission the submission to add.
     */
     void addSubmission(Submission submission);

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
     List<Submission> getAvailableSubmissions();

    /**
     * Gets all the Submission assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
     List<Submission> getSubmissionsByCurator(int curatorId);

     //TODO: Add acceptance submission by sender.

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    void onAcceptance(int submissionId);

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
     * @param user the curator that takes care of the sub.
     */
    void takeChargeOfSubmission(int submissionId, User user);

    /**
     * Returns all the submission of a curator that he took charge of and not already accepted/rejected.
     *
     * @param curatorId id of the curator
     */
    List<Submission> getSubmissionToReview(int curatorId);
}
