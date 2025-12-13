package com.example.SpringTest.IssueManagementSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AgingManager {
    private final IssueQueue issueQueue;
    private final ScheduledExecutorService scheduledExecutor;

    public AgingManager(IssueQueue issueQueue) {
        this.issueQueue = issueQueue;
        this.scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    void start(){
        scheduledExecutor.scheduleAtFixedRate(this::check, 2, 2, TimeUnit.SECONDS);
    }

    void check(){
        for(Priority p : Priority.getHighToLow())
        {
            if(p==Priority.CRITICAL)continue;
            while(!issueQueue.issueQueue.get(p).isEmpty())
            {
                if(issueQueue.issueQueue.get(p).peek().waitTime()>p.waitTime)
                {
                    issueQueue.promote(issueQueue.issueQueue.get(p).peek());
                }
            }
        }
    }

    void shutdown(){
        scheduledExecutor.shutdown();
    }
}
