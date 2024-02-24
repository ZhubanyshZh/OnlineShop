package org.example.domain.model;

import org.example.domain.customInterface.Delivery;

public class Car implements Delivery {
    @Override
    public int getDeliveryCost(String B) {
        switch (B){
            case "Almaty":
                return 500;
            case "Qapshagai", "Taldyqorgan", "Uzynagash", "Qaskelen":
                return 1000;
            case "Shymkent", "Taraz", "Semey", "Qaragandy":
                return 2000;
            default:
                return 0;
        }
    }

    @Override
    public String getDeliveryDate(String B) {
        switch (B){
            case "Almaty", "Qapshagai", "Taldyqorgan", "Uzynagash", "Qaskelen":
                return "Pending 3 hours";
            case "Shymkent", "Taraz", "Semey", "Qaragandy":
                return "Pending 1 day";
            default:
                return null;
        }
    }

}
