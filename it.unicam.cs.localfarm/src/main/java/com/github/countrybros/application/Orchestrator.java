package com.github.countrybros.application;

import com.github.countrybros.application.acceptancesubmission.IAcceptanceSubmissionService;
import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.SevereCodingErrorException;
import com.github.countrybros.application.product.*;
import com.github.countrybros.application.user.ICompanyService;
import com.github.countrybros.model.acceptancesubmission.AcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.AddProductAcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.RecogniseProductAcceptanceSubmission;
import com.github.countrybros.model.acceptancesubmission.SubmissionStatus;
import com.github.countrybros.model.product.Item;
import com.github.countrybros.model.product.ItemStatus;
import com.github.countrybros.web.acceptancesubmission.request.AddProductAcceptanceSubmissionRequest;
import com.github.countrybros.web.product.requests.AddCertificationRequest;
import com.github.countrybros.web.product.requests.AddItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade that represents alla the use cases of the system.
 * <p>
 * Controllers use this class to request use cases,
 * and this manager contacts the services needed to obtain the requested feature.
 */
@Service
public class Orchestrator {

    private final IItemService itemService;
    private final ICompanyService companyService;
    private final ICertificationService certificationService;
    private final IAcceptanceSubmissionService acceptanceSubmissionService;
    private final IStockService stockService;

    public Orchestrator(IItemService itemService, ICompanyService companyService,
                        ICertificationService certificationService,
                        IAcceptanceSubmissionService acceptanceSubmissionService,
                        IStockService stockService) {

        this.itemService = itemService;
        this.companyService = companyService;
        this.certificationService = certificationService;
        this.acceptanceSubmissionService = acceptanceSubmissionService;
        this.stockService = stockService;
    }


    /**
     * Generate a new product and the relative submission to accept/refuse it.
     *
     * @param request the request.
     */
    public void addItemRequest(AddItemRequest request) {

        ItemMapper director = new ItemMapper(companyService, certificationService);

        Item item = director.toDomain(request);
        itemService.addItem(item);

        AddProductAcceptanceSubmissionRequest requestToAdd = new AddProductAcceptanceSubmissionRequest();
        requestToAdd.setItemDetailsId(item.getId());
        requestToAdd.setType("addProduct");
        requestToAdd.setSenderId(request.senderId);

        acceptanceSubmissionService.addAcceptanceSubmission(requestToAdd);
    }

    /**
     * Adds a new certification.
     *
     * @param request the request.
     */
    public void addCertification(AddCertificationRequest request) {

        certificationService.addCertification(request);
    }

    /**
     * Retrives all the Submission that haven't been accepted.
     *
     * @return all the available submission.
     */
    public List<AcceptanceSubmission> getAvailableSubmissions() {

        return acceptanceSubmissionService.getAvailableAcceptanceSubmissions();
    }

    /**
     * Manage the aftermaths of accepting/rejecting a @Submission.
     *
     * @param submissionId the id of the submission.
     * @param accepted     states if the submission have to be accepted or refused.
     */
    public void acceptSubmission(int submissionId, boolean accepted) {

        AcceptanceSubmission submission = acceptanceSubmissionService
                .getAcceptanceSubmission(submissionId);

        if (accepted) {
            acceptanceSubmissionService.onAcceptance(submissionId);
            accept(submission);
        } else {
            acceptanceSubmissionService.onRefusal(submissionId);
            refuse(submission);
        }
    }


    /**
     * Logic behind the acceptance of a submission.
     *
     * @param submission the submission to accept
     */
    private void accept(AcceptanceSubmission submission) {

        if (submission instanceof AddProductAcceptanceSubmission sub)

            itemService.setStatus(ItemStatus.available, sub.getItemDetailsId());

        else if (submission instanceof RecogniseProductAcceptanceSubmission sub) {

            stockService.addQuantityToItem(sub.getProductId(), sub.getQty());
        }

    }

    /**
     * Logic behind the rejection of a submission.
     * <p>
     * Only the request to add a new Item will make some changes.
     *
     * @param submission the submission selected.
     */
    private void refuse(AcceptanceSubmission submission) {

        if (submission instanceof AddProductAcceptanceSubmission sub)
            itemService.deleteItemDetails(sub.getItemDetailsId());
    }
}
