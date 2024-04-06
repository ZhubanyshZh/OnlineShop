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

    public void getAllProducts(Model model){
        List<Product> products = productRepository.findAll();
        if(products!=null){
            model.addAttribute("products", products);
        }
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

    public void orderByDesc(Model model){
        List<Product> products = new ArrayList<>();
        products = productRepository.findByOrderByPriceDesc();

        try{
            if(products.size() != 0){
                model.addAttribute("products", products);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void orderByAsc(Model model){
        List<Product> products = new ArrayList<>();
        products = productRepository.findByOrderByPriceAsc();

        try{
            if(products.size() != 0){
                model.addAttribute("products", products);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllCategories(Model model) {
        List<String> categories = productRepository.findDistinctCategory();

        try{
            if(categories.size()!=0){
                model.addAttribute("categories", categories);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllBrands(Model model) {
        List<String> brands = productRepository.findDistinctProductBrand();

        try{
            if(brands.size()!=0){
                model.addAttribute("brands", brands);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllSizes(Model model){
        List<String> sizes = productRepository.findDistinctSize();

        try{
            if(sizes.size()!=0){
                model.addAttribute("sizes", sizes);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void filer(List<String> categories, List<String> brands, Long minPrice, Long maxPrice, List<String> sizes, Model model){
        List<Product> products = productRepository.findByFilters(categories, brands, minPrice, maxPrice, sizes);

        try{
            if(products.size()!=0){
                model.addAttribute("products", products);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
