package com.example.SpringTest.ordernotificationsystem;




public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Stakeholder c1 = StakeHolderFactory.createStakeHolder(StakeholderType.CUSTOMER, "c1", "customer1");
        c1.addPreferredChannels(EventType.PLACED, "email");
        c1.addPreferredChannels(EventType.SHIPPED, "sms");
        c1.addPreferredChannels(EventType.DELIVERED, "app");

        OrderManager orderManager = new OrderManager();
        Order o1 = orderManager.createOrder("o1",null,"c1","s1","d1");
        orderManager.subscribeForEvent(c1,o1,EventType.SHIPPED);

        o1.setOrderType(EventType.SHIPPED, false);
        c1.addPreferredChannels(EventType.SHIPPED, "email");
        Thread.sleep(2000);


        o1.orderReplay.replay(o1);
    }
}
