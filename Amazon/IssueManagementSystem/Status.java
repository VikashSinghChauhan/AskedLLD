package com.example.SpringTest.IssueManagementSystem;

public enum Status {
    OPEN,
    IN_PROGRESS,
    COMPLETED;


    public Status getNextStatus(Status s)
    {
        switch (s)
        {
            case OPEN : return IN_PROGRESS;
            case IN_PROGRESS: return COMPLETED;
            default: return OPEN;
        }
    }
}
