package com.example.SpringTest.LockerSystem;

interface PlaceProductStragey{
    public Locker placeProduct(Package pkg, LockerStation location, Observer ob);
    public Boolean releaseProduct(Locker locker, String OTP);
}


public class PlaceProduct implements  PlaceProductStragey{

    @Override
    public  Locker placeProduct(Package pkg, LockerStation location, Observer ob)
    {

        synchronized (pkg)
        {
            Locker locker = location.getEmptyLocker(pkg.lockerSize);
            if(locker == null)return null;
            locker.assignLocker(pkg, ob);
            return locker;
        }
    }

    @Override
    public Boolean releaseProduct(Locker locker, String OTP) {
        locker.releaseLocker(OTP);
        return true;

    }


}
