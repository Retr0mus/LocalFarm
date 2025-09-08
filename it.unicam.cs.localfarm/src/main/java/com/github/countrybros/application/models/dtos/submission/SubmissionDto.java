package com.github.countrybros.application.models.dtos.submission;

/**
 * Generic DTO for a submission
 */
public abstract class SubmissionDto {

    public int submissionID;
    public String senderName;
    public String curatorName;
    public String status;
    public String type;
}
