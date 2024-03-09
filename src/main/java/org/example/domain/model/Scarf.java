package org.example.domain.model;

import org.example.domain.model.abst.ImproveStyleDecorator;
import org.example.domain.model.abst.Product;

public class Scarf extends ImproveStyleDecorator {
    public Scarf(Product product){
        super(product);
    }
    @Override
    public void getProductInfo() {
        this.product.getProductInfo();
        System.out.println("\tWith scarf - 3000 KZT");
    }

    @Override
    public int getPrice() {
        return this.product.getPrice() + 3000;
    }
}
