package com.github.countrybros.application;

import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;


/**
 * Interface that represents every possible implementation of AcceptanceSubmissionService
 */
public interface IAcceptanceSubmissionService {

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission);

    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     * @return if the task was successful or not.
     */
    public boolean deleteAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions();

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId);

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    boolean onAcceptance(int submissionId);

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    boolean onRefusal(int submissionId);
}
