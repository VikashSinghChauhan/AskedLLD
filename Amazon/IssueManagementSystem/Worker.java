package com.example.SpringTest.IssueManagementSystem;

public class Worker implements  Runnable {

    private final IssueQueue queue;
    private final String name;

    public Worker(IssueQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }


    @Override
    public void run() {
        while(true)
        {
            Issue issue = queue.poll();
            if(issue == null)
            {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                   System.out.println("Nothing in queue");
                }
                finally {
                    continue;
                }

            }
            else
            {
                System.out.println("Processing :: " + issue.id);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
