package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.JwtAuthToken;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthToken signUp(UserDto userDto) {

        User user = User.builder()
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .birthday(userDto.getBirthday())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .userPassword(passwordEncoder.encode(userDto.getPassword()))
                .role("user")
                .newsNotification("false")
                .build();

        userService.create(user);

        String token = jwtService.generateToken(user);
        return new JwtAuthToken(token);
    }

    public JwtAuthToken login(UserDto userDto) {

    }
}
