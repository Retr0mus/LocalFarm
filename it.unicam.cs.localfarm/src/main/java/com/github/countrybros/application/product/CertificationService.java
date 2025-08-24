package com.github.countrybros.application.product;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.ICertificationRepository;
import com.github.countrybros.model.product.Certification;
import com.github.countrybros.web.product.requests.AddCertificationRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that manages the certifications
 */
@Service
public class CertificationService implements ICertificationService {

    ICertificationRepository repository;

    public CertificationService(ICertificationRepository repository) {

        this.repository = repository;
    }

    @Override
    public void addCertification(AddCertificationRequest request) {

        Certification certification = new Certification(request.name, request.description);
        repository.save(certification);
    }

    @Override
    public List<Certification> getAllCertifications() {

        Iterable<Certification> iterable = repository.findAll();

        List<Certification> list = new ArrayList<>();
        for (Certification c : iterable) {
            list.add(c);
        }

        return list;
    }

    @Override
    public Certification getCertificationById(int id) {

        Certification certification = repository.findById(id).orElse(null);

        if (certification == null) {
            throw new NotFoundInRepositoryException("Certification with id " + id + " not found");
        }

        return certification;
    }
}
