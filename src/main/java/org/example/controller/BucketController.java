package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.service.BucketService;
import org.example.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/Bucket")
public class BucketController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final BucketService bucketService;

    @GetMapping
    public String getBucket(Model model){
        if(loggedUserManagementService.getId() == null){
            return "redirect:/Login?firstLog=true";
        }else{
            model.addAttribute("userName", loggedUserManagementService.getName());

            bucketService.getBucketProducts(model, loggedUserManagementService.getId());
            return "Bucket";
        }
    }

    @GetMapping("/addProductToBucket")
    public String addProductToBucket(
            Model model,
            @RequestParam(name = "id", required = false) String id
    ){
        if(loggedUserManagementService.getId() !=null){
            try{
                Long parseId = Long.parseLong(id);
                bucketService.addProduct(loggedUserManagementService.getId(), parseId);
                return "redirect:/More?id=" + parseId;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return "redirect:/Login?firstLog=true";
    }

    @PostMapping("/subtract")
    public String subtract(Product product, Model model){
        bucketService.subtract(product.getId(), loggedUserManagementService.getId(), model);
        return "redirect:/Bucket";
    }

    @PostMapping("/add")
    public String add(Product product, Model model){
        bucketService.add(product.getId(), loggedUserManagementService.getId(), model);
        return "redirect:/Bucket";
    }
}
