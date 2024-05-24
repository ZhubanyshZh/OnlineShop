package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.FavoriteService;
import org.example.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Favorite")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoriteController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final FavoriteService favoriteService;

    @GetMapping
    public String getFavoriteGage(Model model) {
        if (loggedUserManagementService.getId() != null) {
            model.addAttribute("userName", loggedUserManagementService.getName());
            favoriteService.getProducts(model, loggedUserManagementService.getId());
            return "/Favorite";
        }
        return "redirect:/Login?firstLog=true";
    }

    @GetMapping("/addToFavorite")
    public String addToFavorite(@RequestParam(name = "id", required = false) String id) {
        if (loggedUserManagementService.getId() != null) {
            favoriteService.add(loggedUserManagementService.getId(), Long.parseLong(id));
            return "redirect:/More?id=" + id;
        }
        return "redirect:/Login?firstLog=true";
    }

    @GetMapping("/deleteFavorite")
    public String deleteFavorite(@RequestParam(name = "id", required = false) String id) {
        if (loggedUserManagementService.getId() != null) {
            favoriteService.delete(loggedUserManagementService.getId(), Long.parseLong(id));
            return "redirect:/More?id=" + id;
        }
        return "redirect:/Login?firstLog=true";
    }
}
