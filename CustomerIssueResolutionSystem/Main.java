package com.example.SpringTest.CustomerIssueResolutionSystem;

import ch.qos.logback.core.pattern.util.AsIsEscapeUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AssignmentService assignmentService = AssignmentService.getInstance();

        Agent a1 = new Agent("A1", new ArrayList<>(List.of(1,2,3)));
        Agent a2 = new Agent("A2", new ArrayList<>(List.of(4,5,6)));

        Issue issue= new Issue("I1","O1", 1, "faulty product");

        assignmentService.addAgent(a1);
        assignmentService.addAgent(a2);
        assignmentService.addIssue(issue);
        Agent agent = assignmentService.assign(issue);
        System.out.println("Issue "+issue.issueId + " processed by " + agent.agentId);
        agent.resolveCurrentIssue();
        System.out.println(agent.getResolvedHistory());

    }
}
