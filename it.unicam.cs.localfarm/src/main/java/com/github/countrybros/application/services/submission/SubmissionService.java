package com.github.countrybros.application.services.submission;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repositories.submission.ISubmissionRepository;
import com.github.countrybros.model.submission.Submission;
import com.github.countrybros.model.submission.SubmissionStatus;
import com.github.countrybros.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the item acceptance submissions.
 */
@Service
public class SubmissionService implements ISubmissionService {

    private final ISubmissionRepository submissionRepository;

    public SubmissionService(ISubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    /**
     * Adds an Submission.
     *
     * @param submission the submission to add.
     */
    @Override
    public void addSubmission(Submission submission) {
        submissionRepository.save(submission);
    }

    /**
     * Gets the required Submission.
     *
     * @return the said AcceptanceSubmission.
     */
    @Override
    public Submission getSubmission(int submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Acceptance submission not found with id " + submissionId));
    }

    /**
     * Gets all the free Submission.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<Submission> getAvailableSubmissions() {
        return submissionRepository.findAllByStatus(SubmissionStatus.pending);
    }

    /**
     * Gets all the Submission assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<Submission> getSubmissionsByCurator(int curatorId) {
        return List.of();
    }

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    @Override
    public void onAcceptance(int submissionId) {
        Submission submission = getSubmission(submissionId);

        if (!submission.getStatus().equals(SubmissionStatus.assigned)) {
            throw new ImpossibleRequestException("The submission should be assigned");
        }

        submission.setStatus(SubmissionStatus.accepted);

        submissionRepository.save(submission);
    }

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    @Override
    public void onRejection(int submissionId) {
        Submission submission = getSubmission(submissionId);

        if (!submission.getStatus().equals(SubmissionStatus.assigned)) {
            throw new ImpossibleRequestException("The submission should be assigned");
        }

        submission.setStatus(SubmissionStatus.rejected);

        submissionRepository.save(submission);
    }

    /**
     * Assigns the review on a curator.
     *
     * @param submissionId The sub to assign.
     * @param user       the curator that takes care of the sub.
     */
    @Override
    public void takeChargeOfSubmission(int submissionId, User user) {

        Submission submission = getAcceptanceSubmission(submissionId);

        if (submission.getStatus() == SubmissionStatus.assigned) {
            throw new ImpossibleRequestException("Submission is already assigned");
        }

        submission.setCurator(user);
        submission.setStatus(SubmissionStatus.assigned);
        submissionRepository.save(submission);
    }

    @Override
    public List<Submission> getSubmissionToReview(int curatorId) {

        return submissionRepository.findAllByCuratorIdAndStatus(curatorId, SubmissionStatus.assigned);
    }

    private Submission getAcceptanceSubmission(int submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Acceptance submission not found with id " + submissionId));

    }

}
