package com.example.SpringTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Design a data structure with time-to-live (TTL) where you receive (key, timestamp) pairs (in increasing order). You need to provide:
 * a) Count of all active keys
 * b) Count of active keys for a particular key
 * c) CRUD functions
 *
 * Approach: Map<String, Queue>. At every function call, go through the map and remove expired keys. Also maintain global counters.
 *
 * This approach worked, but the interviewer wanted something like a Queue where Pair = (timestamp, key).
 * This way, instead of iterating over the map for every key, we can directly poll elements from the queue irrespective of the key,
 * and update counts using Map<String,
 * Integer> and a global totalCounter.
 * The worst-case complexity is the same, but this is simpler and has better average-case complexity.
 */

class Pair{
    Long timestamp;
    String key;

    public Pair(Long timestamp, String key) {
        this.timestamp = timestamp;
        this.key = key;
    }

}
public class UberQueTimestampTTL {

    Queue<Pair> queue = new LinkedList<>();
    HashMap<String, Integer> map = new HashMap<>();
    Long ttl = 5000L;
    Integer totalActiveKeys=0;

    void insert(String key, Long timestamp)
    {
        clearExpiredKeys();
        Pair p = new Pair(timestamp, key);
        queue.offer(p);
        map.put(key, map.getOrDefault(key, 0)+1);
        totalActiveKeys++;
    }

    int countAllActiveKeys()
    {
        clearExpiredKeys();
        return map.size();
    }

    void deleteKeys(String key)
    {
        clearExpiredKeys();
//        queue.stream().map(p->{
//            if(p.key.equals(key))queue.remove(p);
//            return null;
//        });

        //option1
        queue.removeIf(p->p.key.equals(key));

        //option2
        //just remove from map, next time when asked check if that key is active in map

        map.remove(key);
    }

    void clearExpiredKeys(){
        while(!queue.isEmpty() && queue.peek().timestamp < System.currentTimeMillis() - ttl)
        {
            Pair p = queue.poll();
            map.put(p.key,map.get(p.key)-1);
            if(map.get(p.key)==0)map.remove(p.key);
            totalActiveKeys--;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        UberQueTimestampTTL uber = new UberQueTimestampTTL();
        Thread watcher  = new Thread(()->{
            while(true)
            {
                System.out.println("Current valid  keys : "+ uber.countAllActiveKeys());
                if(uber.countAllActiveKeys()==0)break;
            }


        });
        for(int i=0;i<26;i++)
        {
            Thread.sleep(100);
            uber.insert("A", System.currentTimeMillis());
        }
        System.out.println("Current valid  keys : "+ uber.countAllActiveKeys());
        System.out.println("Current queue size : " + uber.queue.size());
        uber.deleteKeys("A");
        System.out.println("Current valid  keys : "+ uber.countAllActiveKeys());
        System.out.println("Current queue size : " + uber.queue.size());

//        watcher.start();


    }
}
