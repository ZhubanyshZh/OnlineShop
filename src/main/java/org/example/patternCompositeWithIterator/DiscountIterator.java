package org.example.patternCompositeWithIterator;

import org.example.entity.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DiscountIterator implements Iterator{
    private int position = 0;
    private List<Component> components = new ArrayList<>();

    public DiscountIterator(List<Component> componentList) {
//        Queue<Component> queue = new LinkedList<>();
//        queue.add(components.get(position));
//
//        while (!queue.isEmpty()) {
//            CompositeCategory current = (CompositeCategory) queue.poll();
//            this.components.add(current);
//
//            for (Component subCategory : components) {
//                if (subCategory instanceof CompositeCategory) {
//                    queue.add((CompositeCategory) subCategory);
//                }else this.components.add(subCategory);
//            }
//        }
        List<Component> tempComponents = new ArrayList<>();

        for(Component c: componentList){
            if(c instanceof CompositeCategory){
                for(Component c1: ((CompositeCategory) c).getSubCategories()){
                    tempComponents.add(c1); // Добавляем элементы во временный список
                }
            }
        }
        this.components.addAll(componentList);
        this.components.addAll(tempComponents);
    }

    @Override
    public boolean hasMore() {
        if(components.size() <= position ||
                components.get(position) == null
        ){
            return false;
        }else{
            position++;
            return true;
        }
    }

    @Override
    public String getCurrentCategoryName() {
        return this.components.get(position).getName();
    }

    @Override
    public Component getNext() {
        Component component = this.components.get(position);
        return component;
    }

}
