package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.time.Instant;

enum IssueState{ CREATED, QEUEUED, PROCESSING, RESOLVED}

public class Issue {
        String issueId;
        String orderId;
        Integer issueType;
        String description;
        Agent assignedAgent;
        IssueState issueState;
        String resolution;
        Instant timestamp;

    public Issue(String issueId, String orderId, Integer issueType, String description) {
        this.issueId = issueId;
        this.orderId = orderId;
        this.issueType = issueType;
        this.description = description;
        this.timestamp = Instant.now();
        this.issueState = IssueState.CREATED;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public void resolve(String resolution) {
        this.resolution = resolution;
        setIssueState(IssueState.RESOLVED);
    }

    public void setIssueState(IssueState issueState) {
        this.issueState = issueState;
    }
}
