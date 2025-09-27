package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.Comparator;
import java.util.List;


//Most experienced in solving that kind of issue.
public class MostExperiencedStrategy implements IssueAssignmentStrategy{
    @Override
    public Agent assign(List<Agent> agentList, Issue issue) {
        Agent agent = agentList.stream().max(Comparator.comparingInt(a->a.getOpenIssuesOfType(issue.issueType)))
                .orElse(null);
        agent.enqueueIssue(issue);
        return agent;
    }
}
