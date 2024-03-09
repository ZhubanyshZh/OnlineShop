package org.example.domain.model;

import org.example.domain.model.abst.ImproveStyleDecorator;
import org.example.domain.model.abst.Product;

public class Wristwatch extends ImproveStyleDecorator {

    public Wristwatch(Product product){
        super(product);
    }

    @Override
    public void getProductInfo() {
        this.product.getProductInfo();
        System.out.println("\tWith wristwatch - 20000 KZT");
    }

    @Override
    public int getPrice() {
        return this.product.getPrice() + 20000;
    }
}
