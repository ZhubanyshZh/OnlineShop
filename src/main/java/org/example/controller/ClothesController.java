package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.service.LoggedUserManagementService;
import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Clothes")
public class ClothesController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final ProductService productService;

    @GetMapping
    public String getClothesPage(Model model,
                                 @RequestParam(name = "asc", required = false) String asc,
                                 @RequestParam(name = "desc", required = false) String desc,
                                 @RequestParam(name = "category", required = false) ArrayList<String> categories,
                                 @RequestParam(name = "brand", required = false) ArrayList<String> brands,
                                 @RequestParam(name = "size", required = false) ArrayList<String> sizes,
                                 @RequestParam(name = "priceOt", required = false) Long minPrice,
                                 @RequestParam(name = "priceDo", required = false) Long maxPrice
                                 ){
        if(desc!=null &&
            categories==null &&
                brands==null &&
                    sizes==null &&
                        minPrice==null &&
                            maxPrice==null
        ){
            productService.orderByDesc(model);
        }

        if(asc!=null &&
            categories==null &&
                brands==null &&
                    sizes==null &&
                        minPrice==null &&
                            maxPrice==null
        ){
            productService.orderByAsc(model);
        }

        if(categories!=null ||
            brands!=null ||
                sizes!=null ||
                    minPrice!=null ||
                        maxPrice!=null
        ){
            productService.filer(categories, brands, minPrice, maxPrice, sizes, model);
            model.addAttribute("selectedCategories", categories);
            model.addAttribute("selectedBrands", brands);
            model.addAttribute("selectedSizes", sizes);
            model.addAttribute("enteredMinPrice", minPrice);
            model.addAttribute("enteredMaxPrice", maxPrice);
        }

        if(desc==null &&
            asc==null &&
                categories==null &&
                    brands==null &&
                        sizes==null &&
                            minPrice==null &&
                                maxPrice==null
        ){
            productService.getAllProducts(model);
        }
        model.addAttribute("userName", loggedUserManagementService.getName());
        productService.getAllCategories(model);
        productService.getAllBrands(model);
        productService.getAllSizes(model);

        return "Clothes";
    }
}
