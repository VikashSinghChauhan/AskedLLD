package com.example.SpringTest.ordernotificationsystem;

public class StakeHolderFactory {
    public static Stakeholder createStakeHolder(StakeholderType stakeholderType,
                                                String id,
                                                String name)
    {
        switch (stakeholderType){
            case CUSTOMER:
                return new Customer(id, name, StakeholderType.CUSTOMER);
            case SELLER:
                return new Seller(id,name, StakeholderType.SELLER);
            case DELIVERY_PARTNER:
                return new DeliveryPartner(id, name, StakeholderType.DELIVERY_PARTNER);
            default:
                System.out.println("wrong stakeholder type");
                return null;
        }


    }
}
