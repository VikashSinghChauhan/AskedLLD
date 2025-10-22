package com.example.SpringTest.ordernotificationsystem;

import java.time.Instant;
import java.time.InstantSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class History{
    Order order;
    String timestamp;
    EventType eventType;

    public History(Order order, String timestamp, EventType eventType) {
        this.order = order;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }
}
public class OrderReplay {
    HashMap<Order, List<History>> orderHistoryList = new HashMap<>();

    void insertHistory(Order order, EventType eventType)
    {
        String instant = Instant.now().toString();
        History history = new History(order, instant, eventType);
        orderHistoryList.putIfAbsent(order, new ArrayList<>());
        orderHistoryList.get(order).add(history);
    }

    void replay(Order order)
    {
        List<History> history= orderHistoryList.get(order);
        for(History history1 : history)
        {
            order.setOrderType(history1.eventType,true);
        }

    }
}
