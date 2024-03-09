package org.example.domain.model;

import org.example.domain.model.abst.ImproveStyleDecorator;
import org.example.domain.model.abst.Product;

public class CustomPrint extends ImproveStyleDecorator {
    String customPrint;
    public CustomPrint(Product product, String customPrint){
        super(product);
        this.customPrint = customPrint;
    }

    @Override
    public void getProductInfo() {
        this.product.getProductInfo();
        System.out.println("\tWith custom print (" + this.customPrint + ") - 5000 KZT");
    }

    @Override
    public int getPrice() {
        return this.product.getPrice() + 5000;
    }
}
