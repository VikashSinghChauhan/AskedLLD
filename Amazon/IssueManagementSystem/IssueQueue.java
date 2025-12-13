package com.example.SpringTest.IssueManagementSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//singleton
public class IssueQueue {
    final Map<Priority, Queue<Issue>> issueQueue = new HashMap<>();

    public IssueQueue() {
        for(Priority p : Priority.getHighToLow())
        {
            issueQueue.putIfAbsent(p, new ConcurrentLinkedQueue<>());
        }
    }

    public void submitIssue(Issue issue)
    {
        issueQueue.get(issue.priority).offer(issue);
    }

    public Issue poll(){
        for(Priority p : Priority.getHighToLow())
        {
            if(!issueQueue.get(p).isEmpty())
            {
                return issueQueue.get(p).poll();
            }
        }
        return null;
    }

    synchronized void promote(Issue issue)
    {
        Priority currPriority = issue.priority;
        System.out.println("Promoting issue "+issue.id+ " From " + issue.priority + " to " + issue.priority.next());
        issueQueue.get(currPriority).poll();
        issue.priority = issue.priority.next();
        issueQueue.get(currPriority.next()).offer(issue);
    }



}
