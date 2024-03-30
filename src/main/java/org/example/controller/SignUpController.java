package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/SignUp")
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String getSignUp(){
        return "SignUp.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@RequestParam Map<String, String> map, Model model){

        if(userService.addUser(mapToUserDto(map), model)){
            return "Login.html";
        }else{
            return "SignUp.html";
        }
    }

    public UserDto mapToUserDto(Map<String, String> map){
        UserDto newUserDto = new UserDto();
        newUserDto.setName(map.get("name"));
        newUserDto.setPhoneNumber(map.get("phoneNumber"));
        newUserDto.setBirthday(map.get("birthday"));
        newUserDto.setAddress(map.get("address"));
        newUserDto.setEmail(map.get("email"));
        newUserDto.setPassword(map.get("password"));

        return newUserDto;
    }

//    @PostMapping("/add&deleteUser")
//    public String addListOfUsers(@RequestBody List<UserDto> userDtoList){
//        List<Thread> threads = new ArrayList<>();
//
//        threads.add(new Thread(() -> userService.addUser(userDtoList.get(0))));
//        threads.add(new Thread(() -> userService.addUser(userDtoList.get(1))));
//        threads.add(new Thread(() -> userService.deleteUser(userDtoList.get(2))));
//        threads.add(new Thread(() -> userService.deleteUser(userDtoList.get(3))));
//
//        for(Thread t: threads){
//            t.start();
//        }
//
//        return "Login";
//    }
}