package org.example.domain.model;

import org.example.domain.customInterface.Subscriber;

public class discountSubscriber implements Subscriber {
    private String name;
    private String email;
    public discountSubscriber(String name, String email){
        this.name = name;
        this.email = email;
    }
    public discountSubscriber(){
        this.name = "";
        this.email = "";
    }
    @Override
    public void notifySubscriber() {

    }
}
