package com.github.countrybros.infrastructure.repository;

import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAcceptanceSubmissionRepository extends CrudRepository<AcceptanceSubmission, Integer> {

      /*
        Default function of CrudRepository
        save(…) – save an Iterable of entities. Here, we can pass multiple objects to save them in a batch
        findOne(…) – get a single entity based on passed primary key value
        findAll() – get an Iterable of all available entities in the database
        count() – return the count of total entities in a table
        delete(…) – delete an entity based on the passed object
        exists(…) – verify if an entity exists based on the passed primary key value
     */

    /**
     * Adds an AcceptanceSubmission.
     *
     * @param acceptanceSubmission the submission to add.
     * @return if the task was successful.
     */
    public boolean addAcceptanceSubmission(AcceptanceSubmission acceptanceSubmission);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public boolean removeAcceptanceSubmission(int  acceptanceSubmissionId);

    /**
     * Gets all the free AcceptanceSubmissions.
     *
     * @return a list with all the said AcceptanceSubmission.
     */
    public List<AcceptanceSubmission> getAvailableAcceptanceSubmissions();

    /**
     * Gets all the AcceptanceSubmissions assigned to a certain Curator.
     *
     * @param curatorId the Id of the User with the Curator privileges.
     * @return a list with all the curator's AcceptanceSubmission.
     */
    public List<AcceptanceSubmission> getAcceptanceSubmissionsByCurator(int curatorId);

    /**
     * Gets the required AcceptanceSubmissions.
     *
     * @param acceptanceSubmissionId the Id of the wanted AcceptanceSubmission.
     * @return the said AcceptanceSubmission.
     */
    public AcceptanceSubmission getAcceptanceSubmission(int acceptanceSubmissionId);

    /**
     * Gets all the AcceptanceSubmissions of seller.
     *
     * @param senderId the Id of the User with the seller privileges.
     * @return a list with all the seller's AcceptanceSubmission.
     */
    List<AcceptanceSubmission> getAcceptanceSubmissionsBySender(int senderId);

    boolean onAcceptance(int submissionId);

    boolean onRefusal(int submissionId);
}
