package org.example.domain.model;

import org.example.domain.model.abst.ImproveStyleDecorator;
import org.example.domain.model.abst.Product;

public class Glasses extends ImproveStyleDecorator {

    public Glasses(Product product){
        super(product);
    }

    @Override
    public void getProductInfo() {
        this.product.getProductInfo();
        System.out.println("\tWith glasses - 8000 KZT");
    }

    @Override
    public int getPrice() {
        return this.product.getPrice() + 8000;
    }
}
