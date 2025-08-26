package com.github.countrybros.infrastructure.repository;

import com.github.countrybros.model.acceptancesubmission.Submission;
import org.springframework.data.repository.CrudRepository;

public interface ISubmissionRepository extends CrudRepository<Submission, Integer> {
    
}
