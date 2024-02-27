package org.example.domain.model;

import org.example.domain.customInterface.Subscriber;

public class Discunt_Subscriber implements Subscriber {
    private final String name;
    private final String surname;
    public Discunt_Subscriber(String name,String surname){
        this.name = name;
        this.surname = surname;
    }
    @Override
    public void notifySubscriber() {}
}
