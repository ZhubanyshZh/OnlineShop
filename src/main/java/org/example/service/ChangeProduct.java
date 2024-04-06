package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.repository.Command;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChangeProduct implements Command {

    private final ProductRepository productRepository;
    @Override
    public boolean execute(ProductDto productDto){
        try{
            Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
            Product product = optionalProduct.get();

            ProductDtoToProduct(product, productDto);
            productRepository.save(product);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
}
