/*
Implement an InMemory Task scheduler Library that supports these functionalities:
Submit a task and a time at which the task should be executed. --> schedule(task, time)

1. onetime job
2. recurring job.



*/


package com.example.SpringTest;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Job{

    String msg;

    public Job(String msg) {
        this.msg = msg;
    }

    void execute(){
        System.out.println("Executing job :: "+msg);
    }
}

abstract  class ScheduledJob{
    Job job;
    Long nextExecutionTime;
    Long delay;

    public ScheduledJob(Job job, Long nextExecutionTime) {
        this.job = job;
        this.nextExecutionTime = nextExecutionTime;
    }

    abstract void setNextExecutionTime();
}

class RecurringJob extends ScheduledJob{
    Long delay;
    public RecurringJob(Job job, Long nextExecutionTime, Long delay) {
        super(job, nextExecutionTime);
        this.delay = delay;
    }

    @Override
    void setNextExecutionTime() {
        this.nextExecutionTime = this.nextExecutionTime + this.delay;
    }
}

class OnetimeJob extends ScheduledJob{

    public OnetimeJob(Job job, Long nextExecutionTime) {
        super(job, nextExecutionTime);

    }

    @Override
    void setNextExecutionTime() {
        // not required
    }
}

//currently only one thread dispatcher thread accessing pq so thread safe.
// plan is something like this ::  dispatcher thread --> picks one by one task from pq --> submits it to workerpool
// so inherently thread safe, but priorityblockingqueue can be added.


class ExecutorUtil {
    PriorityQueue<ScheduledJob> pq = new PriorityQueue<>((a,b)->Long.compare(a.nextExecutionTime, b.nextExecutionTime));
    //PriorityBlockingQueue<ScheduledJob> pq = new PriorityBlockingQueue<>(10,(a,b)->
    // Long.compare(a.nextExecutionTime, b.nextExecutionTime));
    ExecutorService workerPool;
    Thread dispatcherThread;
    Integer workerCount;
    Boolean running = true;


    public ExecutorUtil(Integer workerCount){
        this.workerCount = workerCount;
        workerPool =  Executors.newFixedThreadPool(workerCount);
        dispatcherThread = new Thread(this :: dispatchLoop, "Dispatcher Thread");
        dispatcherThread.start();
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    void dispatchLoop() {
        try {

            System.out.println("hello");
            while(running)
            {
                ScheduledJob scheduledjob = pq.peek();
                if(scheduledjob == null)
                {
                    Thread.sleep(100);
                    continue;
                }
                Long currTime = System.currentTimeMillis();
                Long delay = scheduledjob.nextExecutionTime - currTime;

                if (delay  > 0) {
                    Thread.sleep(Math.min(delay, 100));
                    continue;
                }

                pq.poll();
                //lambda expression need variable to be final, never assigned twice
                workerPool.submit(()->{
                    try{
                        scheduledjob.job.execute();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                if(scheduledjob instanceof RecurringJob recurringJob)
                {
                    recurringJob.setNextExecutionTime();
                    pq.offer(recurringJob);
                }
            }

       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
    }

    void shutDown()
    {
        this.running = false;

        workerPool.shutdown();
    }
}


public class JobScheduler {
    public static void main(String[] args) throws InterruptedException {
        Job job1 = new Job("Recurring");
        Job job2 = new Job("Onetime");

        RecurringJob recurringJob = new RecurringJob(job1,System.currentTimeMillis(), 2000L);
        OnetimeJob onetimeJob = new OnetimeJob(job2, System.currentTimeMillis());

        ExecutorUtil executor = new ExecutorUtil(3);
        executor.pq.add(recurringJob);
        executor.pq.add(onetimeJob);

        Thread.sleep(15000);
        executor.shutDown();

    }
}
