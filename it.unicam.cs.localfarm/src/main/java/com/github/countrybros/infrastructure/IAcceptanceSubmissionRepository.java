package com.github.countrybros.infrastructure;

import com.github.countrybros.model.acceptancesubmission.Submission;
import java.util.List;

@Deprecated
public interface IAcceptanceSubmissionRepository {

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param submission the submission to add.
     * @return if the task was successful.
     */
    public boolean addAcceptanceSubmission(Submission submission);

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
    public List<Submission> getAvailableAcceptanceSubmissions();

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    public List<Submission> getAcceptanceSubmissionsByCurator(int curatorId);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public Submission getAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets all the AcceptanceSubmissions of seller.
     *
     * @param senderId the Id of the User with the seller privileges.
     * @return a list with all the seller's AcceptanceSubmission.
     */
    List<Submission> getAcceptanceSubmissionsBySender(int senderId);

    boolean onAcceptance(int submissionId);

    boolean onRefusal(int submissionId);

}
