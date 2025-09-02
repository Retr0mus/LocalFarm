package com.github.countrybros.infrastructure.repositories.submission;

import com.github.countrybros.model.submission.Submission;
import com.github.countrybros.model.submission.SubmissionStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubmissionRepository extends CrudRepository<Submission, Integer> {

    List<Submission> getAcceptanceSubmissionById(int id);

    List<Submission> findAllByStatus(SubmissionStatus status);

    List<Submission> getSubmissionByStatus(SubmissionStatus submissionStatus);

    List<Submission> findAllByCuratorIdAndStatus(int curatorId, SubmissionStatus status);

    // List<AcceptanceSubmission> getAcceptanceSubmissionByCuratorUserId(int id);

      /*
        Default function of CrudRepository
        save(…) – save an Iterable of entities. Here, we can pass multiple objects to save them in a batch
        findOne(…) – get a single entity based on passed primary key value
        findAll() – get an Iterable of all available entities in the database
        count() – return the count of total entities in a table
        delete(…) – delete an entity based on the passed object
        exists(…) – verify if an entity exists based on the passed primary key value
     */

}
