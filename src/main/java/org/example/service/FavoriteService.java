package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Favorite;
import org.example.entity.Product;
import org.example.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public boolean isTheProductFavorite(Long productId, Long userId){
        try{
            return favoriteRepository.isTheProductFavorite(productId, userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void getProducts(Model model, Long userId) {


        try{
            ArrayList<Product> products = favoriteRepository.getAllProductsFromFavorite(userId);
            if(products.size() != 0){
                model.addAttribute("products", products);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public void add(Long userId, Long productId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setProductId(productId);

        try{
            favoriteRepository.save(favorite);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Long userId, Long productId) {

        try{
            favoriteRepository.delete(productId, userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
