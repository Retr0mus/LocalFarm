package com.github.countrybros.application.services.item;

import com.github.countrybros.model.item.Certification;
import com.github.countrybros.application.models.requests.item.AddCertificationRequest;

import java.util.List;

/**
 * Responsibility of managing the certifications
 */
public interface ICertificationService {

    /**
     * Adds a certification to the repository.
     *
     * @param request
     */
    void addCertification(AddCertificationRequest request);

    /**
     * Returns all the Certifications
     *
     * @return a list that contains all the certifications
     */
    List<Certification> getAllCertifications();

    /**
     * Returns a specific Certification, if exists.
     *
     * @param certificationId the specified certification ID.
     *
     * @return the certification searched.
     */
    Certification getCertification(int certificationId);
}
