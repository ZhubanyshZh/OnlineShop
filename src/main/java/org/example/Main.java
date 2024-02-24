package org.example;

import org.example.config.AppConfig;
import org.example.domain.model.Order;
import org.example.domain.model.Shop;
import org.example.domain.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    String[] categories_id = {"Shoes", "T-shirt", "Jeans"};
    static Shop shop = new Shop("Shop");

    public static void main(String[] args) {
//        ArrayList<Product> p = new ArrayList<Product>(List.of(
//                new Shoes("Shoes", 0, "Nike", 30000, 4, "40"),
//                new Jeans("Jeans", 2, "Americano", 15000, 2, "32"),
//                new Tshirt("T-shirts", 1, "Zara", 12000, 3, "M")
//        ));
//        Product p1 = new Shoes("Shoes", 0, "Nike", 30000, 4, "40");
//        p.add(p1);

//        try{
//            Product e = p.get(0);
//            if (e instanceof Shoes){
//                Shoes cloneProduct = (Shoes) ((Shoes) e).clone();
//                cloneProduct.setAmount(cloneProduct.getAmount()-1);
//                System.out.println(cloneProduct.toString());
//            }
//
//        }catch (CloneNotSupportedException e){
//            e.printStackTrace();
//        }

//        cloneProduct.setAmount(cloneProduct.getAmount()-1);

//
//        shop.addProduct(shoes);
//        shop.addProduct(jeans);
//        shop.addProduct(tshirt);
//
//        Feedback feedback = new Feedback("It is good");
//        shoes.addFeedback(feedback);
//
//        for(Product i: p){
//            System.out.println(i.toString()); // Inheritance
////            System.out.println("Feedbacks " + i.getFeedbacks()); // Inheritance
////            i.HowManyFeedbacks(shop, i.getId()); // Полиморфизм, Интерфейс, Association
////            System.out.println();
//        }
//
//        User user = new User("Bekassyl", "Жандосова 55", "87784561212");
//        Bucket bucket = user.createBucket(p); //Use
//
//        System.out.println(bucket.getProducts());
//        Order new_order = new Order();
//
//        for(Products i: p){
//            new_order.addProductToOrder(i);
//        }
//
//        System.out.println("\nProducts in order(): \n");
//        for(Products i: new_order.getProductsInOrder()){
//            i.getProductInfo();
//            System.out.println();
//        }

//        User user1  = new User("Zhubanysh", "Aqtobe", "87783500809");
//        int[] amount = {2, 1, 1};
//        Order order = new Order("19.02.2024", p, user1, amount);
//        order.setDelivery(new Air());
////        Delivery delivery2 = new Air();
////        Delivery delivery3 = new Air();
//        order.getDeliveryDate();
//        order.getDeliveryCost();






        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Shop shop = ctx.getBean("Shop", Shop.class);
        System.out.println(shop);
        User user = ctx.getBean("User", User.class);
        user.setName("Bekassyl");
        System.out.println(shop);
        User user2 = ctx.getBean("User", User.class);
        user2.setName("Zhubanysh");
        System.out.println(shop);

        Order order = ctx.getBean("Order", Order.class);
        order.setUser(user);
        System.out.println(order);
    }
}