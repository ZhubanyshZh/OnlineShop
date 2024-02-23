package org.example.config;

import org.example.Product;
import org.example.Shoes;
import org.example.Shop;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class AppConfig {
    @Bean("Shop")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Shop shop(ArrayList<Product> products){
        Shop shop = new Shop("Men's Shop", products);
        return shop;
    }
}
