package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.aspect.ToLogOurApp;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/Login")
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String getLogin(Model model){
        model.addAttribute("logo", "monclerlogo.webp");
        return "Login";
    }
    @ToLogOurApp
    @PostMapping
    public String authorization(@RequestBody UserDto userDto, Model model){
        if(userService.checkUser(userDto.getEmail(), userDto.getPassword())){
            model.addAttribute("userName", userDto.getName());
            return "HomePage";
        }
        return "Login";
    }
}
