package org.example.patternCompositeWithIterator;

import lombok.Getter;
import org.example.repository.ProductRepository;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
public class CompositeCategory implements Component, IterableCollection{
    private String categoryName;
    private List<Component> subCategories;


    public CompositeCategory(String categoryName){
        this.categoryName = categoryName;
    }

    @Override
    public Iterator createIterator() {
        CompositeCategory Deuce = new CompositeCategory("Deuce");
        Deuce.setSubCategories(new Category("Classic"), new Category("Sportswear"));
        CompositeCategory Top = new CompositeCategory("Top");
        Top.setSubCategories(new Category("Tshirt"));
        CompositeCategory Bottom = new CompositeCategory("Bottom");
        Bottom.setSubCategories(new Category("Jeans"), new Category("Shoes"));

        this.setSubCategories(Deuce, Top, Bottom);
        return new DiscountIterator(subCategories);
    }

    @Override
    public void makeDiscount(int discount, String finishedAt, ProductRepository productRepository) {
        for(Component c: subCategories){
            c.makeDiscount(discount, finishedAt, productRepository);
        }
    }

    @Override
    public String getName() {
        return this.categoryName;
    }

    public void setSubCategories(Component... subCategories) {
        this.subCategories = List.of(subCategories);
    }

    public List<Component> getSubCategories() {
        return subCategories;
    }
}
