package com.example.SpringTest.ordernotificationsystem;


import java.util.HashMap;

public class OrderManager {

    HashMap<String, Order> orderHashMap = new HashMap<>();

    public Order createOrder(String orderId, EventType eventType, String customerId, String sellerId, String deliveryPartnerId)
    {
        Order order = new Order(orderId, eventType, customerId, sellerId, deliveryPartnerId);
        orderHashMap.putIfAbsent(order.orderId, order);
        return order;
    }


    void subscribeForEvent(Stakeholder stakeholder, Order order, EventType eventType)
    {
        switch (eventType){
            case PLACED :
                order.addToplacedChannel(stakeholder);
                return;
            case SHIPPED:
                order.addToshippedChannel(stakeholder);
                return;
            case DELIVERED:
                order.addTodeliveredChannel(stakeholder);
                return;
            default:
                System.out.println("Unknown event");
                return;
        }
    }

    void unSubscribeForEvent(Stakeholder stakeholder, Order order, EventType eventType)
    {
        switch (eventType){
            case PLACED :
                order.removeFromplacedChannel(stakeholder);
                return;
            case SHIPPED:
                order.removeFromshippedChannel(stakeholder);
                return;
            case DELIVERED:
                order.removeFromdeliveredChannel(stakeholder);
                return;
            default:
                System.out.println("Unknown event");
                return;
        }
    }



}
