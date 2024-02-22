package org.example;

public class Train implements Delivery {
    @Override
    public int getDeliveryCost(String B) {
        switch (B){
            case "Shymkent", "Taraz", "Semey", "Qaragandy", "Astana", "Turkistan":
                return 1000;
            case "Aqtobe", "Oral", "Aqtau", "Atyrau", "Pavlodar", "Petropavl", "Qostanai":
                return 2000;
            default:
                return 0;
        }
    }

    @Override
    public String getDeliveryDate(String B) {
        switch (B){
            case "Shymkent", "Taraz", "Semey", "Qaragandy", "Astana", "Turkistan":
                return "Pending 1-1.5 day";
            case "Aqtobe", "Oral", "Aqtau", "Atyrau", "Pavlodar", "Petropavl", "Qostanai":
                return "Pending 3 day";
            default:
                return null;
        }
    }

}
