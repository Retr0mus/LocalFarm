package com.github.countrybros.application.acceptancesubmission;


import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.errors.RequestAlreadySatisfiedException;
import com.github.countrybros.application.user.IUserService;
import com.github.countrybros.infrastructure.repository.ISubmissionRepository;
import com.github.countrybros.infrastructure.repository.IUserRepository;
import com.github.countrybros.model.acceptancesubmission.SubmissionStatus;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.web.acceptancesubmission.request.*;
import com.github.countrybros.model.acceptancesubmission.Submission;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
 */
@Service
public class SubmissionService implements ISubmissionService {

    private ISubmissionRepository acceptanceSubmissionRepository;
    private IUserService userService;
    private SubmissionFactory factory;
    private IUserRepository userRepository;
    private UserRole roles;


    public SubmissionService(ISubmissionRepository acceptanceSubmissionRepository,
                             IUserService userService, IUserRepository userRepository) {
        this.acceptanceSubmissionRepository = acceptanceSubmissionRepository;
        this.factory = new SubmissionFactory();
        this.userService = userService;
        this.userRepository = userRepository;

    }
    /**
     * Adds an AcceptanceSubmission.
     *
     * @param request the submission to add.
     */
    @Override
    public void addAcceptanceSubmission(SubmissionRequest request) {
        Submission submission = factory.create(request);
        acceptanceSubmissionRepository.save(submission);

    }


    /**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
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
    public Submission getAcceptanceSubmission(int acceptanceSubmissionId) {
        return acceptanceSubmissionRepository.findById(acceptanceSubmissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Acceptance submission not found with id " + acceptanceSubmissionId));
    }

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<Submission> getAvailableAcceptanceSubmissions() {
        return acceptanceSubmissionRepository.getAcceptanceSubmissionByStatus(SubmissionStatus.pending);
    }

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<Submission> getAcceptanceSubmissionsByCurator(int curatorId) {
//        return acceptanceSubmissionRepository.getAcceptanceSubmissionByCuratorUserId(curatorId);
        return null;
    }

    /**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    @Override
    public void onAcceptance(int submissionId) {

        Submission submission = getAcceptanceSubmission(submissionId);

        if (submission.getStatus() == SubmissionStatus.accepted) {
            throw new RequestAlreadySatisfiedException("Submission already accepted");
        }

        //TODO: implement
        //userService.getUser(submission.getCuratorId());

        submission.setStatus(SubmissionStatus.accepted);

        acceptanceSubmissionRepository.save(submission);
    }

    /**
     * Refuse the specified AcceptanceSubmission by deleting it.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     */
    @Override
    public void onRefusal(int submissionId) {

        if (!acceptanceSubmissionRepository.existsById(submissionId)) {
            throw new NotFoundInRepositoryException("Submission not found with id " + submissionId);
        }

        acceptanceSubmissionRepository.deleteById(submissionId);
    }

    @Override
    public void takeChargeOfSubmission(int submissionId, int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (!user.getRoles().contains(UserRole.CURATOR)) {
            throw new ImpossibleRequestException("Only curators can take charge of a submission");
        }

        Submission submission = getAcceptanceSubmission(submissionId);

        if (submission.getStatus() == SubmissionStatus.assigned) {
            throw new ImpossibleRequestException("Submission is already assigned");
        }

        submission.setSenderId(userId);
        submission.setStatus(SubmissionStatus.assigned);
        acceptanceSubmissionRepository.save(submission);
    }
}
