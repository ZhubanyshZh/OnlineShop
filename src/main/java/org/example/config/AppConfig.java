package org.example.config;

import org.example.domain.model.*;
import org.example.domain.model.abst.Product;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Configuration
public class AppConfig {
    @Bean("Shop")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Shop getShop(){
        Shop shop = new Shop("Men's Shop");
        return shop;
    }

    @Bean("User")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User getUser(){
        User user = new User();
        Shop shop = getShop();
        shop.addUser(user);
        return user;
    }
    @Bean("Shoes")
    public Shoes getShoes(){
        Shoes shoes = new Shoes();
        Shop shop = getShop();
        shop.addProduct(shoes);
        return shoes;
    }
    @Bean("Jeans")
    public Jeans getJeans(){
        Jeans jeans = new Jeans();
        Shop shop = getShop();
        shop.addProduct(jeans);
        return jeans;
    }
    @Bean("Tshirt")
    public Tshirt getTshirt(){
        Tshirt tshirt = new Tshirt();
        Shop shop = getShop();
        shop.addProduct(tshirt);
        return tshirt;
    }

    @Bean("Order")
    public Order getOrder(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return new Order(formattedDateTime, new ArrayList<Product>());
    }

    @Bean("Car")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Car getCar(){
        return new Car();
    }

    @Bean("Air")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Air getAir(){
        return new Air();
    }

    @Bean("Train")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Train getTrain(){
        return new Train();
    }
    @Bean("Bucket")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Bucket getBacket(){
        return new Bucket();
    }
    @Bean("Feedback")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Feedback getFeedback(){
        return new Feedback();
    }

    @Bean("discountForBirthdayDiscount")
    public discountForBirthdaySubscriber discForBrthdSubs(){
        return new discountForBirthdaySubscriber();
    }

}