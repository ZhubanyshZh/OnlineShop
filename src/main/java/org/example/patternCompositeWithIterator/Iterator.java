package org.example.patternCompositeWithIterator;

import org.example.entity.Product;

public interface Iterator {
    boolean hasMore();
    String getCurrentCategoryName();
    Component getNext();
}
