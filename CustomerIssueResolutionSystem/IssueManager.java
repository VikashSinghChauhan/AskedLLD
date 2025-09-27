package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IssueManager {
    Map<String, Issue> issues = new ConcurrentHashMap<>();

    public Boolean exists(String issueId)
    {
        return issues.containsKey(issueId);
    }

    public Issue getIssue(String issueId){
        return issues.get(issueId);
    }

    public void addIssue(Issue issue){
        issues.put(issue.issueId, issue);
    }

    public ArrayList<Issue> getAllIssues(){
        return new ArrayList<>(issues.values());
    }
}
