package org.example.domain.model;

import org.example.domain.model.abst.Product;

public class Tshirt extends Product implements Cloneable{
    public Tshirt(String name, int cat, String brand, int price, int amount, String size){
        super(name, cat, brand, price, amount, size);
    }

    public Tshirt() {
        super();
    }

    @Override
    public int getPrice() {
        return this.price;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//    @Override
//    public void HowManyFeedbacks(Shop shop, int id) {
//        int rating = 0;
//        int NumberOfFeedbacks = 0;
//        for(Product p: shop.getProducts()){
//            if(id == p.getId()){
//                NumberOfFeedbacks = p.getFeedbacks().size();
//            }
//        }
//        if(NumberOfFeedbacks == 0){
//            System.out.println("There doesn't have any feedbacks");
//            return;
//        }
//        System.out.println("This T-shirt has " + NumberOfFeedbacks + " feedbacks");
//    }
}