package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.Comparator;
import java.util.List;

public class LeastOpenStrategy implements IssueAssignmentStrategy{
    @Override
    public Agent assign(List<Agent> agentList, Issue issue) {
        Agent agent =  agentList.stream()
                .min(Comparator.comparingInt(Agent :: getOpenIssues)).orElse(null);
        agent.enqueueIssue(issue);
        return agent;
    }
}
