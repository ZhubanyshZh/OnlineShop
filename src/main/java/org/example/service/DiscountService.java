package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Category;
import org.example.patternCompositeWithIterator.CompositeCategory;
import org.example.patternCompositeWithIterator.Iterator;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {


    private final CompositeCategory component;
    private final ProductRepository productRepository;

    public boolean makeDiscountByCategory(int discount, String nameOfCategory, String finishedAt){
        Iterator discountIterator = this.component.createIterator();

        while(discountIterator.hasMore()){
            if(discountIterator.getCurrentCategoryName().equals(nameOfCategory)){
                discountIterator.getNext().makeDiscount(discount, finishedAt, productRepository);
                return true;
            }
        }
        return false;
    }
}
