package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HomePage")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("userName", "Beibit");
        return "HomePage";
    }
}
