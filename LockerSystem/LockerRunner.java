package com.example.SpringTest.LockerSystem;

public class LockerRunner {

    public static void main(String[] args) {
        PlaceProductStragey placeProduct = new PlaceProduct();
        LockerStation metro = new LockerStation("1", "Delhi");
        Locker l1 = new Locker("D1",  LockerSize.LARGE);
        Locker l2 = new Locker("D2",  LockerSize.SMALL);

        metro.addLocker(l1);
        metro.addLocker(l2);

        Customer cu = new Customer("vikash", "vikash");
        DeliveryAgent agent = new DeliveryAgent("d1","Ramesh");

        Package p1 = new Package("p1", LockerSize.LARGE, PackageType.DELIVER);
        Locker locker = placeProduct.placeProduct( p1, metro,cu);
        placeProduct.releaseProduct(locker, locker.passcode);


    }


}
