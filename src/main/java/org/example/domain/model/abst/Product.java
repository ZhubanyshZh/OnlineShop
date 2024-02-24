package org.example.domain.model.abst;

import org.example.domain.customInterface.Reviewable;
import org.example.domain.model.Feedback;

import java.util.ArrayList;

public abstract class Product implements Reviewable, Cloneable{
    private static int counter = 1;
    private int id = 1;
    protected String name;
    protected int category_id;
    protected String brand;
    protected int price;
    protected int amount;
    protected String size;
    protected final ArrayList<Feedback> feedbacks;
    public Product(String name, int cat, String brand, int price, int amount, String size){
        this.id = counter++;
        this.name = name;
        this.category_id = cat;
        this.brand = brand;
        this.price = price;
        this.amount = amount;
        this.size = size;
        this.feedbacks = new ArrayList<Feedback>();
    }

    public Product() {
        this.id = counter++;
        this.name = "name";
        this.category_id = 0;
        this.brand = "brand";
        this.price = 0;
        this.amount = 0;
        this.size = "size";
        this.feedbacks = new ArrayList<Feedback>();
    }

    public ArrayList<Feedback> getFeedbacks() {
        return this.feedbacks;
    }
    public void addFeedback(Feedback feedback){
        this.feedbacks.add(feedback);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", size='" + size + '\'' +
                '}' ;
    }

    public int getId() {
        return id;
    }

    public void getProductInfo() {
        System.out.println("This is " + this.name +"\nCategory_id: " + this.category_id + "\nBrand: " + this.brand + "\nPrice: " + this.price + "\nSize: " + this.size);
    }

    public int getCategory() {
        return category_id;
    }
    public String getBrand() {
        return brand;
    }
    public int getPrice() {
        return price;
    }
    public int getAmount() {
        return amount;
    }
    public String getName() {
        return name;
    }
    public String getSize(){
        return this.size;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    //    public Product getProductById(Shop shop, int id){
//        for(Product i: shop.getProducts()){
//            if(i.getId() == id){
//                return i;
//            }
//        }
//
//        return null;
//    }
}