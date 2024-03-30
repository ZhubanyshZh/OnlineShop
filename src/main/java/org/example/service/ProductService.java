package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void getAllProductsToHome(Model model){
        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);
    }

    public boolean addProduct(ProductDto productDto){
        Product product = productDtoToEntity(productDto);

        try {
            productRepository.save(product);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    private Product productDtoToEntity(ProductDto productDto) {
        Product product = new Product();

        setProductDtoToProduct(product, productDto);

        return product;
    }

    public boolean deleteProduct(Long id) {
        try{
            productRepository.deleteById(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean changeProduct(ProductDto productDto) {

        try{
            Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
            Product product = optionalProduct.get();

            setProductDtoToProduct(product, productDto);
            productRepository.save(product);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    public void setProductDtoToProduct(Product product, ProductDto productDto){
        product.setProductName(productDto.getName());
        product.setProductBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());
    }
}
