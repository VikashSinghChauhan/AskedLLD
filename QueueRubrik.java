package com.example.SpringTest;

import java.util.Arrays;
import java.util.Queue;

/***
 * Implement a FIFO Queue:
 *
 * put(int): Adds item into queue
 * get() int: Removes and returns item in queue in FIFO order
 * Requirements:
 *
 * Fix sized memory buffer []int
 * No dynamic memory allocation (not allocating new memory or objects as part of put + get()).
 * Follow up:
 *
 * Support for multiple thread access.
 * Two queues which share this same fix sized memory buffer
 * Need to support FIFO ordering for each queue individually.
 * The size of this int[] (100MB - > 1GB+)
 * We want to make sure usage of this fix sized array can be dynamically adjusted based on each indiv. queue's usage.
 * Minimize memory wastage
 */

class Que{
    int capacity;
    int[] arr;
    int head;
    int tail;

    public Que(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        Arrays.fill(arr,-1);
        this.head=-1;
        this.tail=-1;
    }

    synchronized boolean  put(int a)
    {
        int newtail = (tail+1)%capacity;
        if(arr[newtail]==-1)
        {

            arr[newtail]=a;
            System.out.println("Item placed : "+a+" : at index "+ newtail + " by " + Thread.currentThread().getName().toString());
            tail = newtail;
            return true;
        }
        else
        {
            System.out.println("Item dropped : "+a+" queue already full.");
            return false;
        }
    }

    void printState()
    {
        System.out.println("Current state of Queue :: ");
        for(int i=0;i<capacity;i++)
        {
            System.out.print(arr[i]+" :: ");
        }
    }

    synchronized boolean get()
    {
        int newhead = (head+1)%capacity;
        if(arr[newhead]!=-1)
        {
            int val = arr[newhead];
            arr[newhead] = -1;
            head = newhead;
            System.out.println("Value found : "+ val + " at index "+newhead);
            return true;
        }
        else
        {
            System.out.println("No element found.");
            return false;
        }
    }
}
public class QueueRubrik {

    public static void main(String[] args) throws InterruptedException {
        Que q = new Que(1);
        Thread t1 = new Thread(()->{
            for(int i=0;i<80;i++)q.put(i);
        }, "T1");


        Thread t2 = new Thread(()->{
            for(int i=0;i<30;i++)q.put(81+i);
        }, "T2");

        t1.start();
        t2.start();

        //Thread.sleep(2000) is unreliable - if threads take longer than 2 seconds, you'll have race conditions.
        // Using join() ensures threads complete before you check the state.
       //Thread.sleep(2000);

        t1.join();
        t2.join();

       q.printState();
       q.get();
       q.get();
       q.printState();
       q.put(5);
       q.printState();

    }

}
