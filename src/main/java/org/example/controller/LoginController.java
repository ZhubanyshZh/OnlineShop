package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ChangePasswordDto;
import org.example.dto.UserDto;
import org.example.service.LoggedUserManagementService;
import org.example.service.NotificationService;
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
    private final NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin(
            Model model,
            @RequestParam(name = "firstLog", required = false) String firstLog,
            @RequestParam(name = "loggedSuccess", required = false) boolean loggedSuccess,
            @RequestParam(name = "successChanged", required = false) String successChanged
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

        if(loggedSuccess){
            model.addAttribute("loggedSuccess", true);
        }

        if(successChanged!=null){
            model.addAttribute("successChanged", true);
        }
        return "Login";
    }

    @GetMapping("/ConfirmEmailToChangePassword")
    public String restorePassword(Model model,
                                  @RequestParam(name = "notSuchAccount", required = false) String notSuchAccount){
        if(notSuchAccount!=null){
            model.addAttribute("notSuchAccount",true);
        }
        return "RestorePassword";
    }
    @GetMapping("/ConfirmPassword")
    public String ConfirmPassword(@RequestParam(value = "error", required = false) String error,
                                  Model model){
        if(error!=null){
            model.addAttribute("error", true);
        }
        return "ConfirmPassword";
    }

    @GetMapping("/NewPassword")
    public String NewPassword(@RequestParam(name = "notCorrect", required = false) String error,
                              Model model){
        if(error!=null){
            model.addAttribute("message", loggedUserManagementService.getMessageToUser());
        }
        return "NewPassword";
    }
    @PostMapping("/sendCode")
    public String sendCodeToEmail(
            UserDto userDto, Model model,
            @RequestParam(name = "code", required = false) String code
    ){
        if(code == null){
            if(notificationService.sendCodeToEmail(userDto.getEmail())){
                return "redirect:./ConfirmPassword";
            }else{
                return "redirect:./ConfirmEmailToChangePassword?notSuchAccount";
            }
        }else{
            if(userService.checkCode(code)){
                return "redirect:./NewPassword";
            }else return "redirect:./ConfirmPassword?error";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(ChangePasswordDto changePasswordDto, Model model){
        if(userService.createNewPassword(changePasswordDto, model)){
            return "redirect:../Login?successChanged";
        }else{
            return "redirect:./NewPassword?notCorrect";
        }
    }
}
