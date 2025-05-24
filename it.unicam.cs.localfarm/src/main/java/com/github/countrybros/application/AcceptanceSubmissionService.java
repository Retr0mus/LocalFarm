package com.github.countrybros.application;

import com.github.countrybros.infrastructure.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
public class AcceptanceSubmissionService implements IAcceptanceSubmissionService {
    private IAcceptanceSubmissionRepository acceptanceSubmissionsRepository;

    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        return false;
    }

    public AcceptanceSubmission getAcceptanceSubmission(int submissionId) {
        return null;
    }

    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions() {
        return null;
    }

    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId) {
        return null;
    }

    public List<AcceptanceSubmission> getAcceptanceSubmissionsById(int submissionId) {
        return acceptanceSubmissionsRepository.getAcceptanceSubmissionsById(submissionId);
    }

    public boolean onAcceptance(int submissionId) {
        return acceptanceSubmissionsRepository.onAcceptance(submissionId);
    }

    public boolean onRefusal(int submissionId) {
        return acceptanceSubmissionsRepository.onRefusal(submissionId);
    }


}
