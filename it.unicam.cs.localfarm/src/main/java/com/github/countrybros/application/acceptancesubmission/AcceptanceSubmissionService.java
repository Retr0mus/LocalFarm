package com.github.countrybros.application.acceptancesubmission;

import com.github.countrybros.infrastructure.repository.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
@Service
public class AcceptanceSubmissionService implements IAcceptanceSubmissionService {

    private IAcceptanceSubmissionRepository acceptanceSubmissionRepository;

    public AcceptanceSubmissionService(IAcceptanceSubmissionRepository acceptanceSubmissionRepository) {
        this.acceptanceSubmissionRepository = acceptanceSubmissionRepository;
    }
    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    @Override
    public void addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        acceptanceSubmissionRepository.save(acceptanceSubmission);
    }


    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     * @return if the task was successful or not.
     */
    @Override
    public void deleteAcceptanceSubmission(int acceptanceSubmissionId) {
        acceptanceSubmissionRepository.deleteById(acceptanceSubmissionId);
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
        //TODO check if is correct
        return acceptanceSubmissionRepository.getAcceptanceSubmissionByAccepted(false);
    }

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
        //TODO fix acceptanceSubmissionsbyCurator
        return acceptanceSubmissionRepository.getAcceptanceSubmissionById(curatorId);
    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    @Override
    public void onAcceptance(int submissionId) {

    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     * @return if the task succeeded or not.
     */
    @Override
    public void onRefusal(int submissionId) {

    }
}
