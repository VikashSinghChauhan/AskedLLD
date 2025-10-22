package com.example.SpringTest.ordernotificationsystem;

import java.util.HashMap;

enum StakeholderType{
    CUSTOMER,
    SELLER,
    DELIVERY_PARTNER
}
public abstract class Stakeholder {
    String stakeholderid;
    String name;
    StakeholderType stakeholderType;
    HashMap<EventType, String> preferredChannels;


    public Stakeholder(String stakeholderid, String name, StakeholderType stakeholderType) {
        this.stakeholderid = stakeholderid;
        this.name = name;
        this.stakeholderType = stakeholderType;
        this.preferredChannels = new HashMap<>();
    }

    public void addPreferredChannels(EventType eventType, String channel) {
        preferredChannels.put(eventType, channel);
    }

    public void removePreferredChannels(EventType eventType) {
        preferredChannels.remove(eventType);
    }


    public String getStakeholderid() {
        return stakeholderid;
    }

    public String getName() {
        return name;
    }

    public StakeholderType getStakeholderType() {
        return stakeholderType;
    }
}

class Customer extends Stakeholder{

    public Customer(String stakeholderid, String name, StakeholderType stakeholderType) {
        super(stakeholderid, name, stakeholderType);
    }

}

class Seller extends Stakeholder{
    public Seller(String stakeholderid, String name, StakeholderType stakeholderType) {
        super(stakeholderid, name, stakeholderType);
    }
}

class DeliveryPartner extends Stakeholder{
    public DeliveryPartner(String stakeholderid, String name, StakeholderType stakeholderType) {
        super(stakeholderid, name, stakeholderType);
    }
}
