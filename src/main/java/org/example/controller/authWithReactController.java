package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.*;
@Controller
@RequiredArgsConstructor
@RequestMapping("authWithToken")
public class authWithReactController {

    private final UserService userService;
    private final String SECRET_KEY = "CKXEDQBVK";

    @PostMapping
    public String Login(@RequestBody UserDto userDto, Model model){
        if(userService.checkUser(userDto.getEmail(), userDto.getPassword(), model)){
            String token = Jwts.builder()
                    .setSubject(userDto.getName())
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
            return token;
        } else {
            return "Неправильное имя пользователя или пароль";
        }
    }

    @GetMapping("/check-auth")
    public String checkAuth(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return "Аутентификация прошла успешно";
        } catch (JwtException | IllegalArgumentException e) {
            return "Неверный токен";
        }
    }
}
