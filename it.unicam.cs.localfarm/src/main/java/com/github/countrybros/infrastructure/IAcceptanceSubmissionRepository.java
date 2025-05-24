package com.github.countrybros.infrastructure;

import com.github.countrybros.model.AcceptanceSubmission;
import java.util.List;

public interface IAcceptanceSubmissionRepository {

    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission);
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions();
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId);
    public List<AcceptanceSubmission> getAcceptanceSubmissionsById(int submissionId);

    boolean onAcceptance(int submissionId);

    boolean onRefusal(int submissionId);
}
