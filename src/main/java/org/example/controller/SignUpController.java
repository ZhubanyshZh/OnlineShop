package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/SignUp")
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String getSignUp(){
        return "SignUp";
    }

    @PostMapping
    public String addUser(@RequestBody UserDto userDto, Model model){
        if(userService.addUser(userDto)){
            model.addAttribute("logedsucces", "logedsucces");
            return "Login";
        }else{
            model.addAttribute("logederror", "logederror");
            return "SignUp";
        }
    }

    @PostMapping("/addUsers")
    public String addListOfUsers(@RequestBody List<UserDto> userDtoList){
        List<Thread> threads = new ArrayList<>();
        for(UserDto u: userDtoList){
            threads.add(new Thread(() -> userService.addUser(u)));
        }

        for(Thread t: threads){
            t.start();
        }

        return "Login";
    }
}