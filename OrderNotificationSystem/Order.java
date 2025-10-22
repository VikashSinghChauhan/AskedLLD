package com.example.SpringTest.ordernotificationsystem;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

enum EventType{
    PLACED,
    SHIPPED,
    DELIVERED
}
public class Order {
    String orderId;
    EventType orderType;
    String customerId;
    String sellerId;
    String deliveryPartnerId;
    String notificationMessage;

    List<Stakeholder> placedChannel;
    List<Stakeholder> shippedChannel;
    List<Stakeholder> deliveredChannel;
    OrderReplay orderReplay = new OrderReplay();


    public Order(String orderId, EventType orderType, String customerId, String sellerId, String deliveryPartnerId) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.deliveryPartnerId = deliveryPartnerId;
        placedChannel = new ArrayList<>();
        shippedChannel = new ArrayList<>();
        deliveredChannel = new ArrayList<>();

    }

    public void setOrderType(EventType orderType, boolean replay) {
        if(replay==false)
        {
            this.orderType = orderType;
            orderReplay.insertHistory(this, orderType);
        }
        switch (orderType){
            case PLACED:
                sendNotification(EventType.PLACED,placedChannel);
                return;
            case SHIPPED:
                sendNotification(EventType.SHIPPED,shippedChannel);
                return;
            case DELIVERED:
                sendNotification(EventType.DELIVERED, deliveredChannel);
                return;
            default:
                System.out.println("unknown Event");
                return;
        }
    }

    public void addToplacedChannel(Stakeholder stakeholder){
        placedChannel.add(stakeholder);
    }
    public void addTodeliveredChannel(Stakeholder stakeholder){
        deliveredChannel.add(stakeholder);
    }
    public void addToshippedChannel(Stakeholder stakeholder){
        shippedChannel.add(stakeholder);
    }


    public void removeFromplacedChannel(Stakeholder stakeholder){
        placedChannel.remove(stakeholder);
    }
    public void removeFromdeliveredChannel(Stakeholder stakeholder){
        deliveredChannel.remove(stakeholder);
    }
    public void removeFromshippedChannel(Stakeholder stakeholder){
        shippedChannel.remove(stakeholder);
    }

    void sendNotification(EventType eventType, List<Stakeholder> channel)
    {
        for(Stakeholder stakeholder : channel)
        {
            String msgchannel = stakeholder.preferredChannels.get(eventType);
            String instant = Instant.now().toString();
            System.out.printf("[%s][NOTIFICAION][%s][%s][%s][%s]\n",instant, orderId, eventType.toString(),
                    stakeholder.getStakeholderid(),
                    msgchannel);
        }
    }

}
