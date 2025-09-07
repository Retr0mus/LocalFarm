package com.github.countrybros.application.mappers;

import com.github.countrybros.application.errors.SevereCodingErrorException;
import com.github.countrybros.application.models.dtos.submission.AddProductSubmissionDTO;
import com.github.countrybros.application.models.dtos.submission.RecogniseProductSubmissionDTO;
import com.github.countrybros.application.models.dtos.submission.SubmissionDTO;
import com.github.countrybros.application.services.company.ICompanyService;
import com.github.countrybros.application.services.item.IItemService;
import com.github.countrybros.application.services.stock.IStockService;
import com.github.countrybros.model.submission.AddProductSubmission;
import com.github.countrybros.model.submission.RecogniseProductSubmission;
import com.github.countrybros.application.models.requests.submission.AddProductSubmissionRequest;
import com.github.countrybros.application.models.requests.submission.RecogniseProductSubmissionRequest;
import com.github.countrybros.model.submission.Submission;

import java.util.ArrayList;
import java.util.List;

public class SubmissionMapper {

    private final ICompanyService companyService;
    private final IItemService itemService;
    private final IStockService stockService;

    public SubmissionMapper(ICompanyService companyService, IItemService itemService, IStockService stockService) {

        this.companyService = companyService;
        this.itemService = itemService;
        this.stockService = stockService;
    }

    public RecogniseProductSubmission toDomain(RecogniseProductSubmissionRequest submissionRequest) {
         return new RecogniseProductSubmission(companyService.getCompany(submissionRequest.getSenderId())
                 , stockService.getStock(submissionRequest.getStockId())
                 , submissionRequest.getQta());
    }

    public AddProductSubmission toDomain(AddProductSubmissionRequest submissionRequest) {
        return new AddProductSubmission(companyService.getCompany(submissionRequest.getSenderId()),
                itemService.getItem(submissionRequest.getItemId()));
    }

    public static SubmissionDTO toDTO(Submission submission) {

        if (submission instanceof RecogniseProductSubmission recogniseProductSubmission) {

            RecogniseProductSubmissionDTO dto = new RecogniseProductSubmissionDTO();
            dto.curatorName = recogniseProductSubmission.getCurator().getName();
            dto.senderName = recogniseProductSubmission.getSender().getName();
            dto.status = recogniseProductSubmission.getStatus().toString();
            dto.submissionID = recogniseProductSubmission.getId();
            dto.qta = recogniseProductSubmission.getQta();
            dto.itemName = recogniseProductSubmission.getStock().getItem().getName();

            return dto;
        }
        if (submission instanceof AddProductSubmission addProductSubmission) {

            AddProductSubmissionDTO dto = new AddProductSubmissionDTO();
            dto.curatorName = addProductSubmission.getCurator().getName();
            dto.senderName = addProductSubmission.getSender().getName();
            dto.status = addProductSubmission.getStatus().toString();
            dto.submissionID = addProductSubmission.getId();
            dto.itemName = addProductSubmission.getItem().getName();

            return dto;
        }

        throw new SevereCodingErrorException("Request for submission is not recognised");
    }

    public static List<SubmissionDTO> toDTO(List<Submission> submissions) {

        List<SubmissionDTO> dtos = new ArrayList<>();
        for (Submission submission : submissions) {
            dtos.add(toDTO(submission));
        }

        return dtos;
    }
}
