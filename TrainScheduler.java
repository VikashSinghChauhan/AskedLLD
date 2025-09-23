package com.example.SpringTest;

/*
Second On site round :- machine coding + LLD
it was machine coding round where a problem will be given and candidate is expected to write a working code adhering to design patterns, OOPS concepts and SOLID principles.
do not remeber exact question but it was
there are n platforms and m trains and now we have to schedule these trains on platforms
so below functionalities need to be implemented
we are given a time at which trains is arriving and duration for which it will halt on station so we are suppose to return at which platform it can be scheduled and return the platform number and time at which it will be scheduled.
2. how waiting will be handled, if all platform already has train scheduled on it, how we will schedule next train.
3. if platfrom number is provided with time mentioned, get the train scheduled on that platform.

 */


import java.lang.management.PlatformLoggingMXBean;
import java.util.*;

class Platfrom{
    Integer platformId;
    Long arrivalTime;
    Long timeToEmpty;
    Integer trainId;

    public Platfrom(Integer platformId, Long arrivalTime, Long timeToEmpty, Integer trainId) {
        this.platformId = platformId;
        this.arrivalTime = arrivalTime;
        this.timeToEmpty = timeToEmpty;
        this.trainId = trainId;
    }
}

class Schedule{

    String trainId;

    HashMap<Integer, List<Platfrom>> platformSchedule = new HashMap<>();

    //platform count, minheap
    PriorityQueue<Platfrom> pq = new PriorityQueue<>(2,(a,b)-> Math.toIntExact(a.timeToEmpty - b.timeToEmpty));
    void scheduleTrain(Integer trainId, Long arrival, Long depart)
    {
        if(pq.size()<2) {
            if (!pq.isEmpty() && arrival >= pq.peek().timeToEmpty) {
                int platformId = pq.peek().platformId;
                pq.poll();
                pq.add(new Platfrom(platformId, arrival, depart, trainId));
                platformSchedule.get(pq.size()+1).add(new Platfrom(pq.size(), arrival, depart, trainId));
                System.out.println(trainId + "has been assigned platform number " + platformId + " untill " + depart);
            }
            else
            {
                pq.add(new Platfrom(pq.size()+1,arrival, depart, trainId));
                platformSchedule.computeIfAbsent(pq.size()+1, k->new ArrayList<>());
                platformSchedule.get(pq.size()+1).add(new Platfrom(pq.size(), arrival, depart, trainId));
                System.out.println(trainId + "has been assigned platform number " + pq.size() + " untill " + depart);
            }
        }
        else
        {
            System.out.println("All platform occupied :: ");
            Long earliestTime = pq.peek().timeToEmpty;
            pq.poll();
            scheduleTrain(trainId, earliestTime, earliestTime + depart);
        }
    }
}


public class TrainScheduler {
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        schedule.scheduleTrain(1, 1L,3L);
        schedule.scheduleTrain(2, 2L,5L);
        schedule.scheduleTrain(3, 2L,10L);
        schedule.scheduleTrain(4, 2L,8L);

        for(Map.Entry<Integer, List<Platfrom>> entry : schedule.platformSchedule.entrySet())
        {
            System.out.println("Trains at platform number "+entry.getKey());
            for(Platfrom p : entry.getValue())
            {
                System.out.println("Train "+p.trainId + " Arrival time " + p.arrivalTime + " Departure Time " + p.timeToEmpty);
            }
        }

    }
}
