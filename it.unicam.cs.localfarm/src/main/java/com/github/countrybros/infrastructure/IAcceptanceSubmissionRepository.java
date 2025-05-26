package com.github.countrybros.infrastructure;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import java.util.List;

public interface IAcceptanceSubmissionRepository {

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public boolean removeAcceptanceSubmission(int  acceptanceSubmissionId);

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
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId);

    boolean onAcceptance(int submissionId);

    boolean onRefusal(int submissionId);
}
