package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.List;

public interface IssueAssignmentStrategy {
    Agent assign(List<Agent> agentList, Issue issue);
}
