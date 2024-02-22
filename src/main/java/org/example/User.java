package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private String address;
    private String phone_num;
    private Bucket bucket;
    public User(String name, String address, String phone_num){
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.bucket = new Bucket();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>(List.of(
                new Tshirt("T-Shirt", 1, "Moncler", 15000, 5, "M"),
                new Tshirt("T-Shirt", 1, "Nike", 11000, 3, "L"),
                new Tshirt("T-Shirt", 1, "Moncler", 15000, 6, "XL"),
                new Tshirt("T-Shirt", 1, "Nike", 11000, 2, "XXL"),
                new Shoes("Shoes", 1, "Addidas", 30000, 1, "40"),
                new Shoes("Shoes", 1, "Puma", 25000, 3, "41"),
                new Jeans("Jeans", 1, "Americano", 9500, 6, "31")
        ));
        User user = new User("Zhubaysh", "Aqtobe", "87783500809");
        while(true){
            System.out.println("1 - Show products\n" +
                    "2 - Show Bucket\n" +
                    "0 - Exit");
            int chose = sc.nextInt();
            switch (chose){
                case 1:
                    System.out.println("\nList of all products here:\n");
                    for(Product p: products){
                        System.out.println(p.toString());
                    }
                    boolean flag = true;
                    while(flag){
                        System.out.println("\n1 - Input product id to Add to bucket\n" +
                                "2 - Back\n" +
                                "Chose: ");
                        int chose1 = sc.nextInt();

                        switch (chose1){
                            case 1:
                                System.out.print("Input id: ");
                                int id = sc.nextInt();
                                for(Product p: products){
                                    if(p.getId() == id){
                                        p.setAmount(p.getAmount() - 1);
                                        if(p instanceof Shoes){
                                            Product shoes = (Shoes) ((Shoes)p).clone();
                                            shoes.setAmount(1);
                                            user.bucket.addProduct(shoes);
                                        } else if (p instanceof Jeans) {
                                            Product jeans = (Jeans) ((Jeans)p).clone();
                                            jeans.setAmount(1);
                                            user.bucket.addProduct(jeans);
                                        }else if (p instanceof Tshirt) {
                                            Product tshirt = (Tshirt) ((Tshirt)p).clone();
                                            tshirt.setAmount(1);
                                            user.bucket.addProduct(tshirt);
                                        }
                                    }
                                }
                                System.out.print("Stay show products or go to back(y/N): ");
                                String chose2 = sc.next();
                                if(chose2.equals("y")) flag = true;
                                else flag = false;
                                break;
                            case 2:
                                flag = false;
                                break;
                            default:
                                System.out.println("Don't chose correctly\n");
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nList of all products in your Bucket here:\n");
                    for(Product p: user.bucket.getProducts()){
                        System.out.println(p.toString());
                    }
                    boolean flag1 = true;
                    while(flag1){
                        System.out.println("\n1 - calculate the total of products\n" +
                                "2 - calculate the total sum of delivery and during\n" +
                                "0 - Back\n");
                        int chose2 = sc.nextInt();
                        switch (chose2){
                            case 1:
                                System.out.println("\nTotal sum of products: " + user.bucket.totalSumOfProductsInTheBucket());
                                break;
                            case 2:
                                Order order = new Order("21.02.2024", user.bucket.getProducts(), user);
                                System.out.println("Chose Transport:\n1 - Car\n2 - Train\n3 - Air");
                                int choseTransport = sc.nextInt();
                                if(choseTransport == 1) order.setDelivery(new Car());
                                else if (choseTransport == 2) order.setDelivery(new Train());
                                else order.setDelivery(new Air());
                                order.getDeliveryCost();
                                order.getDeliveryDate();
                                break;
                            case 0:
                                flag1 = false;
                                break;
                            default:
                                System.out.println("Don't chose correctly\n");
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Don't chose correctly\n");
            }
        }
    }
}