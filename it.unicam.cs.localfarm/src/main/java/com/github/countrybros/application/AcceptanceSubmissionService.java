package com.github.countrybros.application;

import com.github.countrybros.infrastructure.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
public class AcceptanceSubmissionService implements IAcceptanceSubmissionService {

    private IAcceptanceSubmissionRepository acceptanceSubmissionRepository;

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    @Override
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        return false;
    }


    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     * @return if the task was successful or not.
     */
    @Override
    public boolean deleteAcceptanceSubmission(int acceptanceSubmissionId) {
        return false;
    }

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    @Override
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId) {
        return null;
    }

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions() {
        return acceptanceSubmissionRepository.getAvailableAcceptanceSubmissions();
    }

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
        return acceptanceSubmissionRepository.getAcceptanceSubmissionsByCurator(curatorId);
    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    @Override
    public boolean onAcceptance(int submissionId) {
        return false;
    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    @Override
    public boolean onRefusal(int submissionId) {
        return false;
    }
}
