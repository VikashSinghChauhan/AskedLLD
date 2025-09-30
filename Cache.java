package com.example.SpringTest;
/*
Asked in oracle OCI
You're working on a video streaming project and you've been asked to design a video buffer
data structure to store a limited number of items.
 The data structure should support four operations: 'add', 'delete', 'getFirst', and 'getEnd'.

The 'add' operation should add an item, return True if it succeeds.
The 'delete' operation should delete the oldest element, return True if it succeeds.
The 'getFirst' operation should retrieve the the oldest item in the data structure.
The 'getEnd' operation should retrieve the newest item in the data structure.

Constraint: Not allowed to use Java Collections Framework.
 */
class Buffer{
    public String data;
    public Buffer prev;
    public Buffer next;

    public Buffer(String data) {
        this.data = data;
        prev = null;
        next = null;
    }
    public Buffer(){}

}
public class Cache {

    Buffer head = new Buffer();
    Buffer tail = new Buffer();
    int count=0;

    public Cache() {
        head.next = tail;
        tail.prev = head;
    }

    boolean add(Buffer buffer)
    {
        // H ->N  T
        Buffer prevTail = tail.prev;
        prevTail.next = buffer;
        buffer.next = tail;

        tail.prev = buffer;
        buffer.prev = prevTail;
        count++;
        return true;
    }

    boolean delete()
    {
        if(count==0)return false;

        head.next = head.next.next;
        head.next.prev = head;
        count--;
        return true;

    }

    Buffer getFirst()
    {
        if(count>0)
        {
            return head.next;
        }
        else
        {
            System.out.println("No element in buffer!");
            return null;
        }
    }

    Buffer getLast()
    {
        if(count>0)
        {
            return tail.prev;
        }
        else
        {
            System.out.println("No element in buffer!");
            return null;
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        cache.getFirst();
        cache.getLast();

        cache.add(new Buffer("first element"));
        Buffer buf = cache.getLast();
        System.out.println(buf.data);
        cache.delete();
        System.out.println(cache.count);

    }

}


