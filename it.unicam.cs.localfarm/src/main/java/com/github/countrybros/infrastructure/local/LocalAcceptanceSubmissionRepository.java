package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocalAcceptanceSubmissionRepository implements IAcceptanceSubmissionRepository {

    private final Map<Integer, AcceptanceSubmission> acceptanceSubmissions = new HashMap<Integer, AcceptanceSubmission>();


    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    @Override
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        return acceptanceSubmissions.put(acceptanceSubmissions.size(), acceptanceSubmission) == null;
    }

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    @Override
    public boolean removeAcceptanceSubmission(int acceptanceSubmissionId) {
        return acceptanceSubmissions.remove(acceptanceSubmissionId) != null;
    }

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions() {
        List<AcceptanceSubmission> availables = new ArrayList<AcceptanceSubmission>();

        for (AcceptanceSubmission submission : acceptanceSubmissions.values()) {
            if(submission.getCurator() == null)
                availables.add(submission);
        }

        return availables;
    }

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
        //TODO: Not implemented because the repositories have to change.
        return null;
    }

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    @Override
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId) {
        return null;
    }

    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsBySender(int senderId) {
        return List.of();
    }

    @Override
    public boolean onAcceptance(int submissionId) {
        return false;
    }

    @Override
    public boolean onRefusal(int submissionId) {
        return false;
    }
}
