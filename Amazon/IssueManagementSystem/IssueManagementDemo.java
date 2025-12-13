package com.example.SpringTest.IssueManagementSystem;

import com.example.SpringTest.CustomerIssueResolutionSystem.IssueManager;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IssueManagementDemo {
    public static void main(String[] args) throws InterruptedException {
        IssueQueue issueQueue = new IssueQueue();
        AgingManager agingManager = new AgingManager(issueQueue);


        Issue issue1 = new Issue(1L, "Issue1","Refund", Priority.LOW);
        Issue issue2 = new Issue(2L, "Issue2", "Defect", Priority.MEDIUM);

        issueQueue.submitIssue(issue1);
        issueQueue.submitIssue(issue2);


        Executor pool = Executors.newFixedThreadPool(10);
        pool.execute(()-> new Worker(issueQueue, "Thread").run());

        agingManager.start();

        for(int i=0;i<200;i++)
        {
            issueQueue.submitIssue(new Issue((long)i, "Issue", "des", Priority.LOW));
            Thread.sleep(50);
        }
    }
}
