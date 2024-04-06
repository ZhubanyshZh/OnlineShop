package org.example.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.repository.Command;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProduct implements Command {

    private final ProductRepository productRepository;
    @Override
    public boolean execute(ProductDto productDto){
        Product product = new Product();
        product.setPhoto(productDto.getPhoto());
        ProductDtoToProduct(product, productDto);

        try {
            productRepository.save(product);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }
}
