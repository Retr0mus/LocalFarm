package com.github.countrybros.application;

import com.github.countrybros.model.AcceptanceSubmission;

import java.util.List;

public interface IAcceptanceSubmissionService {
    AcceptanceSubmission getAcceptanceSubmission(int submissionId);
    List<AcceptanceSubmission> getAvailableAcceptanceSubmissions();
    List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId);
    boolean onAcceptance(int submissionId);
    boolean onRefusal(int submissionId);
}
