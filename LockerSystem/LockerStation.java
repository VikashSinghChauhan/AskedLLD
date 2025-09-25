package com.example.SpringTest.LockerSystem;

import java.util.ArrayList;
import java.util.List;

public class LockerStation {

    String locationUuid;
    String address;
    List<Locker> lockerList;

    public LockerStation(String locationUuid, String address) {
        this.locationUuid = locationUuid;
        this.address = address;
        this.lockerList = new ArrayList<>();
    }

    public void addLocker(Locker locker)
    {
        lockerList.add(locker);
    }

    public String getLocationUuid() {
        return locationUuid;
    }

    public String getAddress() {
        return address;
    }

    public List<Locker> getLockerList() {
        return lockerList;
    }

    Locker getEmptyLocker(LockerSize lockerSize)
    {
        for(Locker l : lockerList)
        {
            if(l.occupied == false && l.lockerSize == lockerSize)
            {
                return l;
            }
        }
        System.out.println("No Empty locker at "+address+" of type "+ lockerSize);
        return null;
    }
}
