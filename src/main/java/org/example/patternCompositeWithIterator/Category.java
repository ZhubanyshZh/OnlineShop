package org.example.patternCompositeWithIterator;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class Category implements Component{
    private String categoryName;
    private List<Product> products;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public void makeDiscount(int discount, String finishedAt, ProductRepository productRepository) {
        this.products = productRepository.findByCategoryName(categoryName);
        for(Product p: products){
            p.setDiscount(discount);
            p.setDiscountCreatedAt(new Date().toString());
            p.setDiscountFinishedAt(finishedAt);
            productRepository.save(p);
        }
    }

    @Override
    public String getName() {
        return this.categoryName;
    }
}
