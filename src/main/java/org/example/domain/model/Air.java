package org.example.domain.model;

import org.example.domain.customInterface.Delivery;

public class Air implements Delivery {
    @Override
    public int getDeliveryCost(String B) {
        switch (B){
            case "Shymkent", "Taraz", "Semey", "Qaragandy", "Astana", "Turkistan":
                return 3000;
            case "Aqtobe", "Oral", "Aqtau", "Atyrau", "Pavlodar", "Petropavl", "Qostanai":
                return 5000;
            default:
                return 0;
        }
    }

    @Override
    public String getDeliveryDate(String B) {
        switch (B){
            case "Shymkent", "Taraz", "Semey", "Qaragandy", "Astana", "Turkistan":
                return "Pending 5 hours";
            case "Aqtobe", "Oral", "Aqtau", "Atyrau", "Pavlodar", "Petropavl", "Qostanai":
                return "Pending 7 hours";
            default:
                return null;
        }
    }

}
