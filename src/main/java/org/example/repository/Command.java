package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

public interface Command {
    boolean execute(ProductDto productDto);
    default void ProductDtoToProduct(Product product, ProductDto productDto, CategoryRepository categoryRepositories){
        product.setProductName(productDto.getName());
        product.setProductBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(categoryRepositories.findByName(productDto.getName()));
    }
}
