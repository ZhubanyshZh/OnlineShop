package org.example;

public class Shoes extends Product implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Shoes)super.clone();
    }

    public Shoes(String name, int cat, String brand, int price, int amount, String size){
        super(name, cat, brand, price, amount, size);
    }

    @Override
    public void HowManyFeedbacks(Shop shop, int id) {
        int rating = 0;
        int NumberOfFeedbacks = 0;
        for(Product p: shop.getProducts()){
            if(id == p.getId()){
                NumberOfFeedbacks = p.getFeedbacks().size();
            }
        }
        if(NumberOfFeedbacks == 0){
            System.out.println("There doesn't have any feedbacks");
            return;
        }
        System.out.println("This Shoes has " + NumberOfFeedbacks + " feedbacks");
    }
}
