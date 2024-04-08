package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class LoggedUserManagementService {
    private Long id;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String address;
    private String email;
    private String password;
    private String role;
    private String NewsNotification;
    private String messageToUser;
}
