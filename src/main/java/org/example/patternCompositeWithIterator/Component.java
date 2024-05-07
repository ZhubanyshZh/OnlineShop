package org.example.patternCompositeWithIterator;

import org.example.repository.ProductRepository;

public interface Component {
    void makeDiscount(int discount, String finishedAt, ProductRepository productRepository);
    String getName();
}
