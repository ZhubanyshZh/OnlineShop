package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("registration")
public class UserController {

    private final UserService userService;


}
