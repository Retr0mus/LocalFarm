package com.github.countrybros.infrastructure.local;

import com.github.countrybros.infrastructure.IAcceptanceSubmissionRepository;
import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;
import java.util.Map;

public class LocalAcceptanceSubmissionRepository implements IAcceptanceSubmissionRepository {

    private Map<Integer, AcceptanceSubmission> acceptanceSubmissions;


    @Override
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission) {
        return false;
    }

    @Override
    public boolean removeAcceptanceSubmission(int acceptanceSubmissionId) {
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
    public AcceptanceSubmission getAcceptanceSubmission(int submissionId) {
        return null;
    }
}
