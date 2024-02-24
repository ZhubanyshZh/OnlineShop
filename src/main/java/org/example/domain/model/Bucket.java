package org.example.domain.model;

import org.example.domain.model.abst.Product;

import java.util.ArrayList;

public class Bucket {
    private ArrayList<Product> products;
    public Bucket(){
        this.products = new ArrayList<>();
    }
    public Bucket(Product product){
        this.products = new ArrayList<>();
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }


    @Override
    public String toString() {
        return "Bucket{" +
                "products=" + products +
                '}';
    }

    public int totalSumOfProductsInTheBucket(){
        int sum = 0;
        for(Product p: this.products){
            sum += p.getPrice();
        }
        return sum;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}