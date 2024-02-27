package org.example.domain.model;

import org.example.domain.model.abst.Product;
import org.example.domain.customInterface.Subscriber;

import java.util.ArrayList;
import java.util.concurrent.Flow;

public class Shop {
    private final String name;
    private final ArrayList<Product> products;
    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<Subscriber> subscribers;

    public Shop(String name){
        this.name = name;
        this.products = new ArrayList<>();
        this.subscribers = new ArrayList<Subscriber>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void addUser(User user){ this.usersList.add(user); }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", products=" + products +
                ", usersList=" + usersList +
                '}';
    }

    public ArrayList<Subscriber> getSubscribers() {
        return subscribers;
    }
    public void unSubscribe(Subscriber s){
        this.subscribers.remove(s);
    }

    public void subscribeToNotify(Subscriber s){
        this.subscribers.add(s);
    }

    public void notifyNewCollectionSubscribers(){
        for(Subscriber s: this.subscribers){
            if (s instanceof newCollectionSubscriber){
                s.notifySubscriber();
            }
        }
    }

    // notifyDiscountSubscribers Beibit

    public void notifyDiscountSubscribers(){
        for(Subscriber s: this.subscribers){
            if(s instanceof discountForBirthdaySubscriber){
                s.notifySubscriber();
            }
        }
    }
}
