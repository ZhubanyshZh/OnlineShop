package org.example.domain.model.abst;

public abstract class ImproveStyleDecorator extends Product{
    protected Product product;
    public ImproveStyleDecorator(){}
    public ImproveStyleDecorator(Product product) {
        this.product = product;
    }
    @Override
    public abstract void getProductInfo();
}
