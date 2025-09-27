package com.example.SpringTest.CustomerIssueResolutionSystem;

import java.util.*;

public class Agent {

    String agentId;
    Issue currentIssue;
    Map<Integer, Integer> expertise = new HashMap<>();
    Queue<Issue> waitlist = new LinkedList<>();
    List<Issue> resolvedHistory = new ArrayList<>();

    public Agent(String agentId, List<Integer> expertiseList) {
        this.agentId = agentId;
        for(Integer type : expertiseList)
        {
            this.expertise.put(type,0);
        }
    }

    public void enqueueIssue(Issue issue)
    {
        waitlist.offer(issue);
        if(currentIssue == null)
        {
            currentIssue = waitlist.poll();
        }
    }

    public void resolveCurrentIssue()
    {
        if(currentIssue == null)return;
        else
        {
            resolvedHistory.add(currentIssue);
            expertise.put(currentIssue.issueType, expertise.get(currentIssue.issueType)+1);
            currentIssue.resolve("resolved issue !!");
            if(waitlist.size()>=1)
            {
                currentIssue = waitlist.poll();
            }
            else
            {
                currentIssue = null;
            }

        }
    }

    public Integer getOpenIssues(){
        return waitlist.size();
    }

    public Integer getOpenIssuesOfType(int type)
    {
        int count=0;
        for(Issue issue : waitlist)
        {
            if(issue.issueType == type)count++;
        }
        return count;
    }

    public Integer getExpertise(int type)
    {
        return expertise.getOrDefault(type, 0);
    }

    List<String> getResolvedHistory(){
        List<String> resolvedIssuesId = new ArrayList<>();
        for(Issue issue : resolvedHistory)
        {
            resolvedIssuesId.add(issue.issueId);
        }
        return resolvedIssuesId;
    }
}
