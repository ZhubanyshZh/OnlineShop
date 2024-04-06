package org.example.repository;

import org.example.dto.ProductDto;
import org.example.entity.Product;

public interface Command {

    boolean execute(ProductDto productDto);
    default void ProductDtoToProduct(Product product, ProductDto productDto){
        product.setProductName(productDto.getName());
        product.setProductBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());
    }
}
