package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.BucketDto;
import org.example.entity.Bucket;
import org.example.entity.Product;
import org.example.repository.BucketRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;

    public boolean haveTheProductThisUser(Long productId, Long userId){

        try{
            return bucketRepository.haveTheProductThisUser(productId, userId);
        }catch (Exception e){
            return false;
        }
    }

    public void addProduct(Long userId, Long productId) {

        try{
            Bucket bucket = new Bucket(null, userId, productId, 1);

            bucketRepository.save(bucket);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getBucketProducts(Model model, Long userId) {

        ArrayList<BucketDto> products = bucketRepository.findProductsByUserId(userId);
        ArrayList<Product> products1 = new ArrayList<>();

        for(BucketDto b : products){
            Product newProduct = getProduct(b);

            products1.add(newProduct);
        }

        if(products1.size() != 0){
            model.addAttribute("products", products1);
        }
    }

    private static Product getProduct(BucketDto b) {
        Product newProduct = new Product();
        newProduct.setId(b.getProduct().getId());
        newProduct.setProductName(b.getProduct().getProductName());
        newProduct.setProductBrand(b.getProduct().getProductBrand());
        newProduct.setPrice(b.getProduct().getPrice());
        newProduct.setSize(b.getProduct().getSize());
        newProduct.setQuantity(b.getQuantity());
        newProduct.setPhoto(b.getProduct().getPhoto());
        newProduct.setCategory(b.getProduct().getCategory());
        return newProduct;
    }

    public void subtract(Long productId, Long userId, Model model) {

        Bucket bucket = bucketRepository.findBucketByUserIdAndProductId(userId, productId);

        bucket.setAmount(bucket.getAmount()-1);

        try{
            if(bucket.getAmount() != 0){
                bucketRepository.save(bucket);
            }else{
                bucketRepository.deleteById(bucket.getId());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void add(Long productId, Long userId, Model model) {

        Bucket bucket = bucketRepository.findBucketByUserIdAndProductId(userId, productId);

        bucket.setAmount(bucket.getAmount()+1);
        bucketRepository.save(bucket);
    }
}
