package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/SignUp")
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String getSignUp(Model model){
        return "SignUp";
    }

    @PostMapping
    public String addUser(UserDto userDto, Model model){

        if(!userService.addUser(userDto, model)){
            return getSignUp(model);
        }else{
            return "redirect:/Login";
        }
    }
}