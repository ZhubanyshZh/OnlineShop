package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.aspect.ToLogOurApp;
import org.example.dto.UserDto;
import org.example.repository.ProductRepository;
import org.example.service.LoggedUserManagementService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/HomePage")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final LoggedUserManagementService loggedUserManagementService;

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("userName", loggedUserManagementService.getName());
        ArrayList<String> linkToHomePageImages = new ArrayList<>();
        linkToHomePageImages.add("homePageImg.jpg");
        linkToHomePageImages.add("homePageImg2.jpg");
        linkToHomePageImages.add("homePageImg3.jpg");
        linkToHomePageImages.add("homePageImg4.jpg");
        model.addAttribute("photos", linkToHomePageImages);
        return "HomePage";
    }

    @ToLogOurApp
    @PostMapping
    public String checkUser(UserDto userDto, Model model){
        if(userService.checkUser(userDto.getEmail(), userDto.getPassword(), model)){
            return "HomePage";
        }
        model.addAttribute("error", true);
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("password", userDto.getPassword());

        return "Login";
    }
}
