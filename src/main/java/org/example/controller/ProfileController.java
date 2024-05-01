package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.dto.ChangePasswordDto;
import org.example.dto.ProductDto;
import org.example.dto.UserDto;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/Profile")
@Setter
public class ProfileController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/addProduct")
    public String addProduct(ProductDto productDto, Model model){

        if(productService.save(productDto, productRepository, model)){
            return "redirect:/Profile?successAdded=true";
        }else{
            return "redirect:/Profile?successAdded=false";
        }
    }



    @PostMapping("/deleteProduct")
    public String deleteProduct(ProductDto productDto){

        if(productService.deleteById(productDto.getId(), productRepository)){
            return "redirect:/Profile?successDeleted=true";
        }else{
            return "redirect:/Profile?successDeleted=false";
        }
    }

    @PostMapping("/changeProduct")
    public String changeProduct(ProductDto productDto){

        if(productService.changeProduct(productDto)){
            return "redirect:/Profile?successChanged=true";
        }else{
            return "redirect:/Profile?successChanged=false";
        }
    }

    @GetMapping
    public String getProfile(Model model,
                             @RequestParam(name = "successAdded", required = false) String successAdded,
                             @RequestParam(name = "successDeleted", required = false) String successDeleted,
                             @RequestParam(name = "successChanged", required = false) String successChanged,
                             @RequestParam(name = "passwordChanged" ,required = false) String passwordChanged,
                             @RequestParam(name = "notify", required = false) String notify
    ){
        if(loggedUserManagementService.getRole()!=null && loggedUserManagementService.getRole().equals("admin")){
            model.addAttribute("role", loggedUserManagementService.getRole());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("products", productRepository.findAllWithCategoryName());
            model.addAttribute("userName", loggedUserManagementService.getName());
            model.addAttribute("role", loggedUserManagementService.getRole());
            model.addAttribute("categories", productService.findCategories());

            if(successAdded != null && successAdded.equals("true")){
                model.addAttribute("added", true);
            }else if(successAdded != null && successAdded.equals("false")){
                model.addAttribute("notAdded", true);
            }else if(successDeleted != null && successDeleted.equals("true")){
                model.addAttribute("deleted", true);
            } else if (successDeleted != null && successDeleted.equals("false")) {
                model.addAttribute("notDeleted", true);
            } else if (successChanged != null && successChanged.equals("true")) {
                model.addAttribute("changed", true);
            } else if (successChanged != null && successChanged.equals("false")) {
                model.addAttribute("notChanged", true);
            } else if (notify != null && notify.equals("true")) {
                model.addAttribute("notify", true);
            } else if (notify != null && notify.equals("false")) {
                model.addAttribute("notnotify", false);
            }

            return "AdminProfile";
        } else if (loggedUserManagementService.getRole()!=null && loggedUserManagementService.getRole().equals("user")){
            model.addAttribute("userName", loggedUserManagementService.getName());
            model.addAttribute("address", loggedUserManagementService.getAddress());
            model.addAttribute("email", loggedUserManagementService.getEmail());
            model.addAttribute("phoneNumber", loggedUserManagementService.getPhoneNumber());
            model.addAttribute("birthday", loggedUserManagementService.getBirthday());
            model.addAttribute("role", loggedUserManagementService.getRole());
            model.addAttribute("id", loggedUserManagementService.getId());
            model.addAttribute("newsNotify", loggedUserManagementService.getNewsNotification());

            if(passwordChanged!=null && passwordChanged.equals("true")){
                model.addAttribute("passwordChanged", true);
            }else if (passwordChanged!=null && passwordChanged.equals("false")){
                model.addAttribute("NotChanged", true);
                model.addAttribute("passwordNotChanged" ,loggedUserManagementService.getMessageToUser());
            }

            return "userProfile";
        } else {

            return "redirect:/Login?firstLog=true";
        }
    }

    @PostMapping("/changeUser")
    public String changeUser(UserDto userDto){

        if(userService.changeUser(userDto)){
            return "redirect:/Profile?successChanged=true";
        }else {
            return "redirect:/Profile?successChanged=false";
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(UserDto userDto){

        if(userService.deleteById(userDto.getId(), userRepository)){
            return "redirect:/Profile?successDeleted=true";
        }else{
            return "redirect:/Profile?successDeleted=false";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(ChangePasswordDto changePasswordDto, Model model){

        if(userService.changePassword(changePasswordDto, model)){
            return "redirect:/Profile?passwordChanged=true";
        }
        return "redirect:/Profile?passwordChanged=false";
    }


    @PostMapping("/changeNotify")
    public String changeNotify(UserDto userDto){
        if(userService.changeNotify(userDto)){
            return "redirect:/Profile?notifyChanged=true";
        }
        return "redirect:/Profile?notifyChanged=false";
    }
}
