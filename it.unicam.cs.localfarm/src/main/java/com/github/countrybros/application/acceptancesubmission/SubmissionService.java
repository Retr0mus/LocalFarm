package com.github.countrybros.application.acceptancesubmission;

import com.github.countrybros.infrastructure.repository.ISubmissionRepository;
import com.github.countrybros.model.acceptancesubmission.Submission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the product acceptance submissions.
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
     * Deletes an Submission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     */
    @Override
    public void deleteSubmission(int acceptanceSubmissionId) {

    }

    /**
     * Gets the required Submission.
     *
     * @param SubmissionId the Id of the wanted Submission.
     * @return the said AcceptanceSubmission.
     */
    @Override
    public Submission getSubmission(int SubmissionId) {
        return null;
    }

    /**
     * Gets all the free Submission.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    @Override
    public List<Submission> getAvailableAcceptanceSubmissions() {
        return List.of();
    }

    /**
     * Gets all the Submission assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    @Override
    public List<Submission> getAcceptanceSubmissionsByCurator(int curatorId) {
        return List.of();
    }

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    @Override
    public void onAcception(int submissionId) {

    }

    /**
     * Accepts the specified Submission.
     *
     * @param submissionId the id of the Submission.
     */
    @Override
    public void onRejection(int submissionId) {

    }

    /**
     * Assigns the review on a curator.
     *
     * @param submissionId The sub to assign.
     * @param userId       the curator that takes care of the sub.
     */
    @Override
    public void takeChargeOfSubmission(int submissionId, int userId) {

    }

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param request the submission to add.
     *//*
    @Override
    public void addSubmission(AddProductSubmissionRequest request) {
        Submission submission = factory.create(request);
        acceptanceSubmissionRepository.save(submission);

    }


    *//**
     * Deletes an AcceptanceSubmission.
     *
     * @param acceptanceSubmissionId the submission to delete.
     *//*
    @Override
    public void deleteSubmission(int acceptanceSubmissionId) {
        acceptanceSubmissionRepository.deleteById(acceptanceSubmissionId);
    }

    *//**
     * Gets the required AcceptanceSubmissions.
     *
     * @param SubmissionId the id of the wanted Submission.
     * @return the said AcceptanceSubmission.
     *//*
    @Override
    public Submission getSubmission(int SubmissionId) {
        return acceptanceSubmissionRepository.findById(SubmissionId)
                .orElseThrow(() -> new NotFoundInRepositoryException("Acceptance submission not found with id " + SubmissionId));
    }

    *//**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     *//*
    @Override
    public List<Submission> getAvailableAcceptanceSubmissions() {
        return acceptanceSubmissionRepository.getSubmissionByAccepted(false);
    }

    *//**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     *//*
    @Override
    public List<Submission> getAcceptanceSubmissionsByCurator(int curatorId) {
//        return acceptanceSubmissionRepository.getAcceptanceSubmissionByCuratorUserId(curatorId);
        return null;
    }

    *//**
     * Accepts the specified AcceptanceSubmission.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     *//*
    @Override
    public void onAcception(int submissionId) {

        Submission submission = getSubmission(submissionId);

        if (submission.isAccepted()) {
            throw new RequestAlreadySatisfiedException("Submission already accepted");
        }

        //TODO: implement
        //userService.getUser(submission.getCuratorId());

        submission.setAccepted(true);

        acceptanceSubmissionRepository.save(submission);
    }

    *//**
     * Refuse the specified AcceptanceSubmission by deleting it.
     *
     * @param submissionId the id of the AcceptanceSubmission.
     *//*
    @Override
    public void onRejection(int submissionId) {

        if (!acceptanceSubmissionRepository.existsById(submissionId)) {
            throw new NotFoundInRepositoryException("Submission not found with id " + submissionId);
        }

        acceptanceSubmissionRepository.deleteById(submissionId);
    }

    @Override
    public void takeChargeOfSubmission(int submissionId, int userId) {

        //check that user exists
        userService.getUser(userId);

        Submission submission = getSubmission(submissionId);

        if (submission.isAccepted()) {
            throw new ImpossibleRequestException("Submission is already accepted");
        }

        submission.setSenderId(userId);
        acceptanceSubmissionRepository.save(submission);
    }*/
}
