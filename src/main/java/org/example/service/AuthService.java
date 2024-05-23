package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.dto.*;
import org.example.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoggedUserManagementService loggedUserManagementService;

    public SignUpDto signUp(UserDto userDto) {
        SignUpDto signUpDto = new SignUpDto();
        if(!validation(userDto, signUpDto)){
            return signUpDto;
        }

        User user = User.builder()
                .id(null)
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .birthday(userDto.getBirthday())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .userPassword(passwordEncoder.encode(userDto.getPassword()))
                .role("user")
                .newsNotification("false")
                .build();

        return userService.create(user);
    }

    public JwtAuthToken login(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getEmail(),
                userDto.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(userDto.getEmail());

        var jwt = jwtService.generateToken(user);
        HomePageDto homePageDto = HomePageDto.builder()
                .userName(user.getUsername())
                .build();
        return new JwtAuthToken(jwt, homePageDto);
    }

    private boolean validation(UserDto userDto, SignUpDto signUpDto){

        String nameError = "", phoneNumError = "", birthdayError = "", addressError = "", emailError = "", passwordError = "";
        if (userDto.getName().isEmpty()) {
            nameError += "name is empty";
            signUpDto.setNameError(nameError);
        }

        if (userDto.getPhoneNumber().isEmpty()) {
            phoneNumError += "phone number is empty";
            signUpDto.setPhoneNumError(phoneNumError);
        }

        if (userDto.getPhoneNumber().length() != 11 && !userDto.getPhoneNumber().isEmpty()) {
            phoneNumError += "phone number must have 11 nums";
            signUpDto.setPhoneNumError(phoneNumError);
        }

        if (userDto.getBirthday().isEmpty()) {
            birthdayError += "birthday is empty";
            signUpDto.setBirthdayError(birthdayError);
        }

        if (userDto.getAddress().isEmpty()) {
            addressError += "address is empty";
            signUpDto.setAddressError(addressError);
        }

        if (userDto.getEmail().isEmpty()) {
            emailError += "email is empty";
            signUpDto.setEmailError(emailError);
        }

        if (!EmailValidator.getInstance().isValid(userDto.getEmail()) && emailError=="") {
            emailError += "email isn't valid";
            signUpDto.setEmailError(emailError);
        }

        if (userDto.getPassword().isEmpty()) {
            passwordError += "password is empty";
            signUpDto.setPasswordError(passwordError);
        } else {
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher1 = pattern.matcher(userDto.getPassword());

            pattern = Pattern.compile("[A-Z]");
            Matcher matcher2 = pattern.matcher(userDto.getPassword());

            pattern = Pattern.compile("[a-z]");
            Matcher matcher3 = pattern.matcher(userDto.getPassword());

            pattern = Pattern.compile("[.,:;@&%$]");
            Matcher matcher4 = pattern.matcher(userDto.getPassword());

            if (!matcher1.find()) {
                passwordError += "password must have nums [0-9]";
                signUpDto.setPasswordError(passwordError);
            } else if (!matcher2.find()) {
                passwordError += "Пароль должен содержать буквы A-Z";
                signUpDto.setPasswordError(passwordError);
            } else if (!matcher3.find()) {
                passwordError += "Пароль должен содержать буквы a-z";
                signUpDto.setPasswordError(passwordError);
            } else if (!matcher4.find()) {
                passwordError += "Пароль должен содержать знаки";
                signUpDto.setPasswordError(passwordError);
            } else if (userDto.getPassword().length() <= 8) {
                passwordError += "Пароль должен содержать 8 символов";
                signUpDto.setPasswordError(passwordError);
            }
        }

        if (nameError.isEmpty() &&
                phoneNumError.isEmpty() &&
                birthdayError.isEmpty() &&
                addressError.isEmpty()
                && emailError.isEmpty() &&
                passwordError.isEmpty()
        ) {
            return true;
        } else return false;
    }
}
