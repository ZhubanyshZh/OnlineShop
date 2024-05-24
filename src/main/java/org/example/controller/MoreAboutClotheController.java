package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.BucketService;
import org.example.service.FavoriteService;
import org.example.service.LoggedUserManagementService;
import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/More")
public class MoreAboutClotheController {

    private final ProductService productService;
    private final BucketService bucketService;
    private final LoggedUserManagementService loggedUserManagementService;
    private final FavoriteService favoriteService;

    @GetMapping
    public String getMore(@RequestParam(name = "id", required = false) String id,
                          Model model) {
        if (id != null && !id.isEmpty()) {
            try {
                Long userId = loggedUserManagementService.getId();
                Long parseId = Long.parseLong(id);

                productService.getCachedProductById(parseId, model);

                model.addAttribute("isProductInTheBucket",
                        bucketService.haveTheProductThisUser(parseId, userId));
                model.addAttribute("userName", loggedUserManagementService.getName());
                model.addAttribute("isInFavorite", favoriteService.isTheProductFavorite(parseId, userId));

                return "MoreClothe";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "redirect:/Clothes";
            }
        } else {
            return "redirect:/Clothes";
        }
    }


}
