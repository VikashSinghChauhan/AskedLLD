package com.example.SpringTest.IssueManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Priority {
    CRITICAL(Long.MAX_VALUE),
    HIGH(10000L),
    MEDIUM(7000L),
    LOW(5000L);

    Long waitTime;

    Priority(Long waitTime) {
        this.waitTime = waitTime;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public  Priority next(){
        switch (this){
            case LOW : return MEDIUM;
            case MEDIUM: return HIGH;
            case HIGH: return CRITICAL;
            default: return CRITICAL;

        }
    }

    public static List<Priority> getHighToLow(){
        return new ArrayList<>(Arrays.asList(CRITICAL, HIGH, MEDIUM, LOW));
    }


}
