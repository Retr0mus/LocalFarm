package com.github.countrybros.web.acceptancesubmission.request;

public class AcceptanceSubmissionRequest {
    private int id;
    private String type;
    private int curatorId;
    private int senderId;
    private boolean accepted =false;

    public String getType() {
        return type;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
