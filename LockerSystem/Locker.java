package com.example.SpringTest.LockerSystem;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Locker {

    String lockerUuid;
    Boolean occupied;
    LockerSize lockerSize;
    Package pkg;
    String passcode;
    Long placingTime;
    List<Observer> observerList;


    public Locker(String lockerUuid, LockerSize lockerSize) {
        this.lockerUuid = lockerUuid;
        occupied = false;
        this.lockerSize = lockerSize;
        this.observerList = new ArrayList<>();
    }

    public synchronized void assignLocker(Package pkg, Observer ob)
    {
        this.pkg = pkg;
//        String passwd = UUID.randomUUID().toString().substring(0,6);
        String passwd = String.valueOf((Math.random()*1000000)).substring(0,6);
        this.passcode = passwd;
        this.occupied=(true);
        placingTime = Instant.now().toEpochMilli();
        observerList.add(ob);


        for(Observer obb : observerList)
        {
            obb.notify("Your OTP for package "+ pkg.productId+" "+passwd);
        }
    }

    public synchronized Package releaseLocker(String passwd)
    {
        if(occupied && passwd.equals(passcode))
        {
            Long timeElasped = Instant.now().toEpochMilli() - placingTime;
            timeElasped = timeElasped/(1000*60*60);
            Package temp = pkg;
            pkg = null;
            passcode = null;
            occupied = (false);
            placingTime = null;


            for(Observer ob : observerList)
            {
                ob.notify("Package released "+ temp.productId+" Thank you ! + time " + timeElasped + " hours");
            }
            observerList.clear();
            return temp;
        }
        else {
            throw new RuntimeException("Incorrect passcode, try again");
        }
    }

    private void notifyObserver(String action)
    {
        System.out.println(action);
    }
}
