package com.example.SpringTest.LockerSystem;
/*
I think when deliver, otp should go to customer
and in return otp should  go to delivery person.

Based on this, observer list will change, or logic will chagne in notify step.
 */
enum PackageType {DELIVER, RETURN}

public class Package {
    String productId;
    LockerSize lockerSize;
    PackageType packageType;

    public Package(String productId, LockerSize lockerSize, PackageType packageType) {
        this.productId = productId;
        this.lockerSize = lockerSize;
        this.packageType = packageType;
    }
}
