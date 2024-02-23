package org.example.config;

import org.example.Product;
import org.example.Shoes;
import org.example.Shop;
import org.example.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class AppConfig {
    @Bean("Shop")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Shop Shop(){
        Shop shop = new Shop("Men's Shop");
        shop.addProduct(new Shoes("Shoes", 1, "Nike", 25000, 5, "40"));
        shop.addUser(new User());
        return shop;
    }
}
