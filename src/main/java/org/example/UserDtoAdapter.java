package org.example;

import org.example.dto.UserDto;
import org.example.entity.User;

public class UserDtoAdapter {
    private User user;

    public UserDtoAdapter(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setBirthday(userDto.getBirthday());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setUserPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setNewsNotification(userDto.getNewsNotification());

        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
