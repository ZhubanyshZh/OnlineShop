package org.example;

import java.util.ArrayList;

public class Shop {
    private final String name;
    private final ArrayList<Product> products;
    private ArrayList<User> usersList = new ArrayList<>();

    public Shop(String name){
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void addUser(User user){ this.usersList.add(user); }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
