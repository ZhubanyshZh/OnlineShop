package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.repository.Command;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProduct implements Command {

    private final ProductRepository productRepository;
    @Override
    public boolean execute(ProductDto productDto){
        try{
            productRepository.deleteById(productDto.getId());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
