package com.github.countrybros.application.product;

import com.github.countrybros.model.product.Certification;
import com.github.countrybros.web.product.requests.AddCertificationRequest;

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
     * Returns a specific Certification, if exists
     *
     * @param id the specified certification ID
     *
     * @return the certification searched
     */
    Certification getCertificationById(int id);
}
