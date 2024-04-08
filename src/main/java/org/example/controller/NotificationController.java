package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.NotificationDto;
import org.example.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/newsNotificationWithEmail")
    public String notifyUsersAboutNews(NotificationDto notificationDto){
        if(notificationService.notifyUsers(notificationDto)){
            return "redirect:/Profile?notify=true";
        }
        return "redirect:/Profile?notify=false";
    }
}
