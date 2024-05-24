package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.aspect.ToLogOurApp;
import org.example.dto.ChangePasswordDto;
import org.example.dto.JwtAuthToken;
import org.example.dto.JwtAuthenticationResponse;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/Login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;
    private final LoggedUserManagementService loggedUserManagementService;
    private final NotificationService notificationService;
    private final AuthService authService;
    private final ProductService productService;


    @ToLogOurApp
    @PostMapping
    public String authUser(UserDto userDto, Model model) {
//        if (userService.checkUser(userDto)) {
        try {
            JwtAuthToken jwt = authService.login(userDto);
            if(jwt.getToken() != null && !jwt.getToken().isEmpty()){
                User user = userService.getByUserEmail(userDto.getEmail());
                loggedUserManagementService.setId(user.getId());
                loggedUserManagementService.setName(user.getName());
                loggedUserManagementService.setPhoneNumber(user.getPhoneNumber());
                loggedUserManagementService.setBirthday(user.getBirthday());
                loggedUserManagementService.setAddress(user.getAddress());
                loggedUserManagementService.setEmail(user.getEmail());
                loggedUserManagementService.setPassword(user.getPassword());
                loggedUserManagementService.setRole(user.getRole());
                loggedUserManagementService.setNewsNotification(user.getNewsNotification());
                model.addAttribute("products", productService.findAll());
                model.addAttribute("userName", loggedUserManagementService.getName());
                model.addAttribute("token", jwt.getToken());
                return "redirect:/HomePage";
            }else {
                model.addAttribute("error", true);
                model.addAttribute("email", userDto.getEmail());
                model.addAttribute("password", userDto.getPassword());
                return "Login";
            }
        }catch (Exception e){
            model.addAttribute("error", true);
            model.addAttribute("email", userDto.getEmail());
            model.addAttribute("password", userDto.getPassword());
            return "Login";
        }
    }

    @PostMapping("/newLogin")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public JwtAuthToken loginUser(@RequestBody UserDto userDto, Model model) {
        return authService.login(userDto);
    }

    @GetMapping
    public String getLogin(
            Model model,
            @RequestParam(name = "firstLog", required = false) String firstLog,
            @RequestParam(name = "loggedSuccess", required = false) boolean loggedSuccess,
            @RequestParam(name = "successChanged", required = false) String successChanged
    ) {
        model.addAttribute("logo", "monclerlogo.webp");
        if (loggedUserManagementService.getId() != null) {
            loggedUserManagementService.setId(null);
            loggedUserManagementService.setName(null);
            loggedUserManagementService.setPhoneNumber(null);
            loggedUserManagementService.setBirthday(null);
            loggedUserManagementService.setAddress(null);
            loggedUserManagementService.setEmail(null);
            loggedUserManagementService.setPassword(null);
            loggedUserManagementService.setRole(null);
        }

        if (firstLog != null && firstLog.equals("true")) {
            model.addAttribute("firstLog", true);
        }

        if (loggedSuccess) {
            model.addAttribute("loggedSuccess", true);
        }

        if (successChanged != null) {
            model.addAttribute("successChanged", true);
        }
        return "Login";
    }

    @GetMapping("/ConfirmEmailToChangePassword")
    public String restorePassword(Model model,
                                  @RequestParam(name = "notSuchAccount", required = false) String notSuchAccount) {
        if (notSuchAccount != null) {
            model.addAttribute("notSuchAccount", true);
        }
        return "RestorePassword";
    }

    @GetMapping("/ConfirmPassword")
    public String ConfirmPassword(@RequestParam(value = "error", required = false) String error,
                                  Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "ConfirmPassword";
    }

    @GetMapping("/NewPassword")
    public String NewPassword(@RequestParam(name = "notCorrect", required = false) String error,
                              Model model) {
        if (error != null) {
            model.addAttribute("message", loggedUserManagementService.getMessageToUser());
        }
        return "NewPassword";
    }

    @PostMapping("/sendCode")
    public String sendCodeToEmail(
            UserDto userDto, Model model,
            @RequestParam(name = "code", required = false) String code
    ) {
        if (code == null) {
            if (notificationService.sendCodeToEmail(userDto.getEmail())) {
                return "redirect:./ConfirmPassword";
            } else {
                return "redirect:./ConfirmEmailToChangePassword?notSuchAccount";
            }
        } else {
            if (userService.checkCode(code)) {
                return "redirect:./NewPassword";
            } else return "redirect:./ConfirmPassword?error";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(ChangePasswordDto changePasswordDto, Model model) {
        if (userService.createNewPassword(changePasswordDto, model)) {
            return "redirect:../Login?successChanged";
        } else {
            return "redirect:./NewPassword?notCorrect";
        }
    }
}
