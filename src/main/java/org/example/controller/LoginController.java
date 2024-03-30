package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.LoggedUserManagementService;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/Login")
public class LoginController {

    private final UserService userService;
    private final LoggedUserManagementService loggedUserManagementService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin(
            Model model,
            @RequestParam(name = "firstLog", required = false) String firstLog
    ) {
        model.addAttribute("logo", "monclerlogo.webp");
        if(loggedUserManagementService.getId() != null){
            loggedUserManagementService.setId(null);
            loggedUserManagementService.setName(null);
            loggedUserManagementService.setPhoneNumber(null);
            loggedUserManagementService.setBirthday(null);
            loggedUserManagementService.setAddress(null);
            loggedUserManagementService.setEmail(null);
            loggedUserManagementService.setPassword(null);
            loggedUserManagementService.setRole(null);
        }

        if(firstLog!=null && firstLog.equals("true")){
            model.addAttribute("firstLog", true);
        }
        return "Login.html";
    }
}
