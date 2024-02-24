package org.example.domain.model;

import org.example.domain.customInterface.Delivery;
import org.example.domain.model.abst.Product;

import java.util.ArrayList;

public class Order {
    private static int counter = 1;
    private int id;
    private String date;
    private String status;
    private ArrayList<Product> listProducts = new ArrayList<>();
    private User user;
    private Delivery delivery;
    public Order(){

    }
    public Order(String date, ArrayList<Product> products){
        this.id = counter++;
        this.date = date;
        this.status = "Pending";
        for(Product p: products){
            this.listProducts.add(p);
        }
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

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", listProducts=" + listProducts +
                ", user=" + user +
                ", delivery=" + delivery +
                '}';
    }

    public int getId() {return id;}
    public String getDate() {return date;}
    public String getStatus() {return status;}
    public User getUser() {return user;}
    public ArrayList<Product> getListProducts(){return this.listProducts;}
    public int getTotalPrice(){return 0;}
}
// Виды рассылок скидка, новое поступление, скидка на день рождение,