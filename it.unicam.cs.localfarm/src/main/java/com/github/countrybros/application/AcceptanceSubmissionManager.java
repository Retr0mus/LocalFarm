package com.github.countrybros.application;

import com.github.countrybros.infrastructure.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
public class AcceptanceSubmissionManager implements IAcceptanceSubmissionRepository {
    private List<AcceptanceSubmission> acceptanceSubmissions;


    @Override
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        return false;
    }

    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions() {
        return null;
    }

    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
        return null;
    }

    @Override
    public List<AcceptanceSubmission> getAcceptanceSubmissionsById(int submissionId) {
        return null;
    }



}
