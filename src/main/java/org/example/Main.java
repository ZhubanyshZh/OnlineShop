package org.example;

import org.example.config.AppConfig;
import org.example.domain.customInterface.Subscriber;
import org.example.domain.model.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    String[] categories_id = {"Shoes", "T-shirt", "Jeans"};
//    static Shop shop = new Shop("Shop");

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
//        System.out.println(shop);
//        User user = ctx.getBean("User", User.class);
//        user.setName("Bekassyl");
//        System.out.println(shop);
//        User user2 = ctx.getBean("User", User.class);
//        user2.setName("Zhubanysh");
//        System.out.println(shop);
//
//        Order order = ctx.getBean("Order", Order.class);
//        order.setUser(user);
//        System.out.println(order);






        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Shop shop = ctx.getBean("Shop", Shop.class);
//        Subscriber subscriber1 = ctx.getBean("discountForBirthdayDiscount", discountForBirthdaySubscriber.class);
//        ((discountForBirthdaySubscriber) subscriber1).setName("Dias");
//        ((discountForBirthdaySubscriber) subscriber1).setEmail("kapashov.009@mail.ru");

//        Subscriber subscriber2 = ctx.getBean("discountForBirthdayDiscount", discountForBirthdaySubscriber.class);
//        ((discountForBirthdaySubscriber) subscriber2).setName("Zhubanysh");
//        ((discountForBirthdaySubscriber) subscriber2).setEmail("zhubanysh.zharylkassynov@narxoz.kz");
//
//        Subscriber subscriber3 = ctx.getBean("discountForBirthdayDiscount", discountForBirthdaySubscriber.class);
//        ((discountForBirthdaySubscriber) subscriber3).setName("Bekassyl");
//        ((discountForBirthdaySubscriber) subscriber3).setEmail("bekassyl.serik@narxoz.kz");

//        shop.subscribeToNotify(subscriber1);
//        shop.subscribeToNotify(subscriber2);
//        shop.subscribeToNotify(subscriber3);



//        shop.notifyDiscountForBirthdaySubscribers();
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter your email: ");
        String email = sc.next();

        while(flag){
            System.out.println(
                    "1 - Add notification\n" +
                            "2 - Delete notification\n" +
                            "3 - Show my notifications\n" +
                            "4 - Notify Subscribers\n" +
                            "0 - Exit"
            );
            System.out.print("\nInput: ");
            int choose1 = sc.nextInt();
            boolean flag2 = true;
            while(flag2){
                switch (choose1){
                    case 1:
                        System.out.println("1 - Subscribe for \"Birthday Discount\" notification\n" +
                                "2 - Subscribe for \"Discount\" notification\n" +
                                "3 - Subscribe for \"New Collection\" notification\n" +
                                "0 - Back");
                        System.out.print("\nInput: ");
                        int choose2 = sc.nextInt();
                        switch (choose2){
                            case 1:
                                boolean alreadySubscribeForBirthdayDiscount = false;
                                for(Subscriber s: shop.getSubscribers()){
                                    if (s instanceof discountForBirthdaySubscriber && ((discountForBirthdaySubscriber) s).getEmail() == email){
                                        alreadySubscribeForBirthdayDiscount = true;
                                    }
                                }
                                if(alreadySubscribeForBirthdayDiscount) System.out.println("Yor already subscribe to this notification!");
                                else{
                                    Subscriber subscriber = ctx.getBean("discountForBirthdayDiscount", discountForBirthdaySubscriber.class);
                                    ((discountForBirthdaySubscriber) subscriber).setName(name);
                                    ((discountForBirthdaySubscriber) subscriber).setEmail(email);
                                    shop.subscribeToNotify(subscriber);
                                    System.out.println("\nSuccessfully Subscribe to \"Birthday Discount\" notification\n");
                                }
                                break;
                            case 2:
                                boolean alreadySubscribetoDiscount = false;
                                for(Subscriber s: shop.getSubscribers()){
                                    if (s instanceof discountSubscriber && ((discountSubscriber) s).getEmail() == email){
                                        alreadySubscribetoDiscount = true;
                                    }
                                }
                                if(alreadySubscribetoDiscount) System.out.println("Yor already subscribe to this notification!");
                                else{
                                    Subscriber subscriber2 = ctx.getBean("discountSubscriber", discountSubscriber.class);
                                    ((discountSubscriber) subscriber2).setName(name);
                                    ((discountSubscriber) subscriber2).setEmail(email);
                                    shop.subscribeToNotify(subscriber2);
                                    System.out.println("\nSuccessfully Subscribe to \"Discount\" notification\n");
                                }
                                break;
                            case 3:
                                boolean alreadySubscribeToNewCollection = false;
                                for(Subscriber s: shop.getSubscribers()){
                                    if (s instanceof newCollectionSubscriber && ((newCollectionSubscriber) s).getEmail() == email){
                                        alreadySubscribeToNewCollection = true;
                                    }
                                }
                                if(alreadySubscribeToNewCollection) System.out.println("Yor already subscribe to this notification!");
                                else{
                                    Subscriber subscriber3 = ctx.getBean("newCollectionDiscount", newCollectionSubscriber.class);
                                    ((newCollectionSubscriber) subscriber3).setName(name);
                                    ((newCollectionSubscriber) subscriber3).setEmail(email);
                                    shop.subscribeToNotify(subscriber3);
                                    System.out.println("\nSuccessfully Subscribe to \"New Collection\" notification\n");
                                }
                                break;
                            case 0:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Don't choose correct!");
                        }
                        break;
                    case 2:
                        System.out.println("1 - Unsubscribe from \"Birthday Discount\" notification\n" +
                                "2 - Unsubscribe from \"Discount\" notification\n" +
                                "3 - Unsubscribe from \"New Collection\" notification\n" +
                                "0 - Back");
                        System.out.print("\nInput: ");
                        int choose3 = sc.nextInt();
                        switch (choose3){
                            case 1:
                                for(Subscriber s: shop.getSubscribers()){
                                    if(s instanceof discountForBirthdaySubscriber && ((discountForBirthdaySubscriber) s).getEmail() == email){
                                        shop.unSubscribe(s);
                                        System.out.println("\nSuccessfully unsubscribe from \"Birthday Discount\" notification\n");
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                for(Subscriber s: shop.getSubscribers()){
                                    if(s instanceof discountSubscriber && ((discountSubscriber) s).getEmail() == email){
                                        shop.unSubscribe(s);
                                        System.out.println("\nSuccessfully unsubscribe from \"Discount\" notification\n");
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                for(Subscriber s: shop.getSubscribers()){
                                    if(s instanceof newCollectionSubscriber && ((newCollectionSubscriber) s).getEmail() == email){
                                        shop.unSubscribe(s);
                                        System.out.println("\nSuccessfully unsubscribe from \"New Collection\" notification\n");
                                        break;
                                    }
                                }
                                break;
                            case 0:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Don't choose correct!");
                        }
                        break;
                    case 3:
                        System.out.println("Your notifications:");
                        for(Subscriber s: shop.getSubscribers()){
                            if(s instanceof discountForBirthdaySubscriber && ((discountForBirthdaySubscriber) s).getEmail() == email){
                                System.out.println("Discount For Birthday\n");
                            } else if (s instanceof discountSubscriber && ((discountSubscriber) s).getEmail() == email) {
                                System.out.println("Discount\n");
                            } else if (s instanceof newCollectionSubscriber && ((newCollectionSubscriber) s).getEmail() == email) {
                                System.out.println("New Collection\n");
                            }
                        }
                        flag2 = false;
                        break;
                    case 4:
                        shop.notifyDiscountForBirthdaySubscribers();
                        shop.notifyNewCollectionSubscribers();
                        shop.discountSubscriber();
                        System.out.println("Successfully notify!!!");
                        flag2 = false;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Don't choose correctly!");
                }
            }
        }
    }
}