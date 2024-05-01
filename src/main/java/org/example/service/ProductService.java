package org.example.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Category;
import org.example.dto.DTO;
import org.example.dto.ProductDto;
import org.example.entity.CustomEntity;
import org.example.entity.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService extends MyService {
    private final CategoryRepository categoryRepository;

    private static final JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
    private ObjectMapper mapper = new ObjectMapper();
    public ProductService(ProductRepository productRepository, UserRepository userRepository, LoggedUserManagementService loggedUserManagementService, CategoryRepository categoryRepository) {
        super(productRepository, userRepository, loggedUserManagementService);
        this.categoryRepository = categoryRepository;
    }

    public void getCachedProductById(Long id, Model model){
        Product product = new Product();
        try(Jedis jedis = jedisPool.getResource()) {
            String key = "product:%d".formatted(id);
            String raw = jedis.get(key);
            if(raw != null){
                model.addAttribute("product", mapper.readValue(raw, Product.class));
            }else{
                product = productRepository.findById(id).get();
                model.addAttribute("product", product);
                jedis.setex(key, 60L, mapper.writeValueAsString(product));
            }

        }catch (JsonParseException e){
            System.out.println(e.getMessage());
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllProducts(Model model){
        List<Product> products = productRepository.findAll();
        if(products!=null){
            model.addAttribute("products", products);
        }
    }

    public void getCachedAllProducts(Model model){
        try(Jedis jedis = jedisPool.getResource()){
            List<Product> products = new ArrayList<>();
            String key = "products";
            List<Product> products1 = jedis.lrange(key, 0, -1).stream()
                    .map(p -> {
                        try{
                            return mapper.readValue(p, Product.class);
                        } catch (JsonMappingException e) {
                            throw new RuntimeException(e);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toList());
            if(products1.size() != 0){
                model.addAttribute("products", products1);
            }else{
                products = productRepository.findAll();
                if(products!=null){
                    model.addAttribute("products", products);

                    products.stream()
                            .map(product -> {
                                try {
                                    return mapper.writeValueAsString(product);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            }).forEach(p -> jedis.rpush(key, p));
                    jedis.expire(key, 300L);
                }
            }
        }
    }

    @Override
    protected CustomEntity dtoToEntity(DTO dto) {
        Product product = new Product();

        setProductDtoToProduct(product, (ProductDto) dto);

        return product;
    }

    public boolean changeProduct(ProductDto productDto) {

        try{
            Product product = productRepository.findById(productDto.getId()).get();

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
        Category category = categoryRepository.findByName(productDto.getName());
        product.setCategory(category);
    }

    public void orderByDesc(Model model){
        List<Product> products = productRepository.findByOrderByPriceDesc();

        try{
            if(products.size() != 0){
                model.addAttribute("products", products);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void orderByAsc(Model model){
        List<Product> products = productRepository.findByOrderByPriceAsc();

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



    //    private Product dtoToEntity(ProductDto productDto) {
//        Product product = new Product();
//
//        setProductDtoToProduct(product, productDto);
//
//        return product;
//    }

//    public boolean deleteProduct(Long id) {
//        try{
//            productRepository.deleteById(id);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//        return true;
//    }
    //    public boolean addProduct(ProductDto productDto){
//        Product product = (Product) dtoToEntity(productDto);
//
//        try {
//            productRepository.save(product);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//
//        return true;
//    }
}
