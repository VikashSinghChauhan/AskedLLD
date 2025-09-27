package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private static final AssignmentService instance = new AssignmentService();
    AgentManager agentManager;
    IssueManager issueManager;

    IssueAssignmentStrategy assignmentStrategy;



    private AssignmentService() {
        this.agentManager = new AgentManager();
        this.issueManager = new IssueManager();
        this.assignmentStrategy = new LeastOpenStrategy();
    }

    public static AssignmentService getInstance()
    {
        return instance;
    }


    public Agent assign(Issue issue)
    {
        List<Agent> agentList = agentManager.getAllAgents();
        Agent agent = assignmentStrategy.assign(agentList, issue);
        return agent;
    }

    public void addAgent(Agent agent){
        agentManager.addAgent(agent);
    }

    public void addIssue(Issue issue)
    {
        issueManager.addIssue(issue);
    }

}
