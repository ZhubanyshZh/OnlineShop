package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.dto.ChangePasswordDto;
import org.example.dto.ProductDto;
import org.example.dto.UserDto;
import org.example.repository.Command;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private Command command;

    @PostMapping("/addProduct")
    public String addProduct(ProductDto productDto){

        setCommand(new AddProduct(productRepository));

        if(command.execute(productDto)){
            return "redirect:/Profile?successAdded=true";
        }else{
            return "redirect:/Profile?successAdded=false";
        }
    }
//    @PostMapping("/addProduct")
//    public String addProduct(ProductDto productDto, Model model){
//
//        if(productService.addProduct(productDto)){
//            return "redirect:/Profile?successAdded=true";
//        }else{
//            return "redirect:/Profile?successAdded=false";
//        }
//    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(ProductDto productDto){
        setCommand(new DeleteProduct(productRepository));

        if(command.execute(productDto)){
            return "redirect:/Profile?successDeleted=true";
        }else{
            return "redirect:/Profile?successDeleted=false";
        }
    }

//    @PostMapping("/deleteProduct")
//    public String deleteProduct(ProductDto productDto){
//
//        if(productService.deleteProduct(productDto.getId())){
//            return "redirect:/Profile?successDeleted=true";
//        }else{
//            return "redirect:/Profile?successDeleted=false";
//        }
//    }

    @PostMapping("/changeProduct")
    public String changeProduct(ProductDto productDto){

        setCommand(new ChangeProduct(productRepository));

        if(command.execute(productDto)){
            return "redirect:/Profile?successChanged=true";
        }else{
            return "redirect:/Profile?successChanged=false";
        }
    }
//    @PostMapping("/changeProduct")
//    public String changeProduct(ProductDto productDto){
//
//        if(productService.changeProduct(productDto)){
//            return "redirect:/Profile?successChanged=true";
//        }else{
//            return "redirect:/Profile?successChanged=false";
//        }
//    }

    @GetMapping
    public String getProfile(Model model,
                             @RequestParam(name = "successAdded", required = false) String successAdded,
                             @RequestParam(name = "successDeleted", required = false) String successDeleted,
                             @RequestParam(name = "successChanged", required = false) String successChanged,
                             @RequestParam(name = "passwordChanged" ,required = false) String passwordChanged
    ){
        if(loggedUserManagementService.getRole()!=null && loggedUserManagementService.getRole().equals("admin")){
            model.addAttribute("role", loggedUserManagementService.getRole());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("userName", loggedUserManagementService.getName());
            model.addAttribute("role", loggedUserManagementService.getRole());

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
            }

            return "AdminProfile";
        }else if (loggedUserManagementService.getRole()!=null && loggedUserManagementService.getRole().equals("user")){
            model.addAttribute("userName", loggedUserManagementService.getName());
            model.addAttribute("address", loggedUserManagementService.getAddress());
            model.addAttribute("email", loggedUserManagementService.getEmail());
            model.addAttribute("phoneNumber", loggedUserManagementService.getPhoneNumber());
            model.addAttribute("birthday", loggedUserManagementService.getBirthday());
            model.addAttribute("role", loggedUserManagementService.getRole());
            model.addAttribute("id", loggedUserManagementService.getId());

            if(passwordChanged!=null && passwordChanged.equals("true")){
                model.addAttribute("passwordChanged", true);
            }else if (passwordChanged!=null && passwordChanged.equals("false")){
                model.addAttribute("NotChanged", true);
                model.addAttribute("passwordNotChanged" ,loggedUserManagementService.getMessageToUser());
            }

            return "userProfile";
        }else {

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

        if(userService.deleteUser(userDto.getId())){
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

}
