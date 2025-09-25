package com.example.SpringTest.LockerSystem;

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
            Package temp = pkg;
            pkg = null;
            passcode = null;
            occupied = (false);

            for(Observer ob : observerList)
            {
                ob.notify("Package released "+ temp.productId+" Thank you !");
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
