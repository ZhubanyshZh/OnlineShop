package org.example;

import java.util.ArrayList;

public class Order {
    private int id = 1;
    private String date;
    private String status;
    private ArrayList<Product> listProducts = new ArrayList<>();
    private User user;
    private Delivery delivery;
    public Order(String date, ArrayList<Product> products, User user){
        this.id++;
        this.date = date;
        this.status = "Pending";
        for(Product p: products){
            this.listProducts.add(p);
        }
        this.user = user;
    }
    public void setDelivery(Delivery delivery) {this.delivery = delivery;}
    public void getDeliveryCost(){
        String B = this.user.getAddress();
        if(this.delivery.getDeliveryCost(B) == 0){
            System.out.println("There is no delivery to your city using this transport");
            return;
        }
        System.out.println("Total sum of delivery: " + this.delivery.getDeliveryCost(B) + " KZT");
    }
    public void getDeliveryDate(){
        String B = this.user.getAddress();
        if(this.delivery.getDeliveryDate(B) == null){
            System.out.println("There is no delivery to your city using this transport");
            return;
        }
        System.out.println("Pending will be: " + this.delivery.getDeliveryDate(B));
    }
    public int getId() {return id;}
    public String getDate() {return date;}
    public String getStatus() {return status;}
    public User getUser() {return user;}
    public ArrayList<Product> getListProducts(){return this.listProducts;}
    public int getTotalPrice(){return 0;}
}
// Виды рассылок скидка, новое поступление, скидка на день рождение,