package com.example.SpringTest.LockerSystem;

public interface Observer {

    void notify(String action);
}

class Customer implements Observer{
    String customerId;
    String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    @Override
    public void notify(String action) {
        System.out.println(name + " " + action);
    }
}


class DeliveryAgent implements Observer {
    String agentId;
    String name;

    public DeliveryAgent(String customerId, String name) {
        this.agentId = customerId;
        this.name = name;
    }

    @Override
    public void notify(String action) {
        System.out.println(name + " " + action);
    }
}
