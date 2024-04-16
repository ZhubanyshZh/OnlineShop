package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/SignUp")
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public String getSignUp(Model model){
        return "SignUp";
    }

    @PostMapping
    public String addUser(UserDto userDto, Model model){

        if(!userService.save(userDto, userRepository, model)){
            return getSignUp(model);
        }else{
            return "redirect:/Login?loggedSuccess=true";
        }
    }
}