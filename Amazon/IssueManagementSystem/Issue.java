package com.example.SpringTest.IssueManagementSystem;

import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

public class Issue {
    private static AtomicLong Counter = new AtomicLong(1);

    final Long id;
    final Long ownerId;
    final String title;
    final String description;
    final Status status;
    Priority priority;
    final Long createdTimeStamp;


    public Issue(Long ownerId, String title, String description, Priority priority) {
        this.id = Counter.getAndIncrement();
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.status = Status.OPEN;
        this.priority = priority;
        this.createdTimeStamp = Instant.now().toEpochMilli();
    }

    Long waitTime(){
        return Instant.now().toEpochMilli() - createdTimeStamp;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", ownerId=" + ownerId +

                ", status=" + status +
                ", priority=" + priority +
                ", age=" + waitTime() +
                '}';
    }
}
