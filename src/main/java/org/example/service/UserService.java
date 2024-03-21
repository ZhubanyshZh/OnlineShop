package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.entity.User;
import org.example.dto.UserDto;
import org.example.repository.SingletonUserRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean addUser(UserDto userDto){
        String nameError ="", phoneNumError="", birthdayError="", addressError="", emailError="", passwordError = "";

        if(userDto.getName().isEmpty()) nameError+="\nName is empty<br>";
        if(userDto.getPhoneNumber().isEmpty()) phoneNumError+="\nPhone Number is empty<br>";
        if(userDto.getPhoneNumber().length() != 11) phoneNumError+="\nPhone Number must have 11 nums<br>";
        if(userDto.getBirthday().isEmpty()) birthdayError+="\nBirthday is Empty<br>";
        if(userDto.getAddress().isEmpty()) addressError+="\nAddress is empty<br>";
        if(userDto.getEmail().isEmpty()) emailError+="\nEmail is empty<br>";
        if(!EmailValidator.getInstance().isValid(userDto.getEmail())) emailError+="Email isn't valid<br>";
        if(userDto.getPassword().isEmpty()) passwordError+="\nУкажите пароль<br>";
        else {
            Pattern pattern = Pattern.compile("[0-9]");
            Matcher matcher = pattern.matcher(userDto.getPassword());
            if(!matcher.find()) {
                passwordError += "Пароль должен содержать цифры 0-9<br>";
            }

            pattern = Pattern.compile("[A-Z]");
            matcher = pattern.matcher(userDto.getPassword());
            if(!matcher.find()) {
                passwordError += "Пароль должен содержать буквы A-Z<br>";
            }

            pattern = Pattern.compile("[a-z]");
            matcher = pattern.matcher(userDto.getPassword());
            if(!matcher.find()) {
                passwordError += "Пароль должен содержать буквы a-z<br>";
            }

            pattern = Pattern.compile("[.,:;@&%$]");
            matcher = pattern.matcher(userDto.getPassword());
            if(!matcher.find()) {
                passwordError += "Пароль должен содержать знаки<br>";
            }

            if(userDto.getPassword().length() <= 8) {
                passwordError += "Пароль должен содержать 8 символов<br>";
            }
        }

        List<User> users = userRepository.findAll();

        boolean haveAlreadyAccount = false;

        for(User u: users){
            if(u.getEmail().equals(userDto.getEmail()) ||
                u.getPhoneNumber().equals(userDto.getPhoneNumber()))
            {
                haveAlreadyAccount = true;
                break;
            }
        }

        if(nameError.isEmpty() &&
            phoneNumError.isEmpty() &&
            birthdayError.isEmpty() &&
            addressError.isEmpty()
            && emailError.isEmpty() &&
            passwordError.isEmpty()
            && !haveAlreadyAccount
        ){
            User user = userDtoToUser(userDto);
            try{
                userRepository.save(user);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
            return true;
        }else return false;
    }

    public boolean checkUser(String email, String password){
        List<User> users = userRepository.findAll();

        for(User u : users){
            if(u.getEmail().equals(email) && u.getUserPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    public User userDtoToUser(UserDto userDto){
        User user = new User();

        user.setName(userDto.getName().trim());
        user.setPhoneNumber(userDto.getPhoneNumber().trim());
        user.setBirthday(userDto.getBirthday());
        user.setAddress(userDto.getAddress().trim());
        user.setEmail(userDto.getEmail().trim());
        user.setUserPassword(userDto.getPassword().trim());

        return user;
    }

    public void deleteUser(UserDto userDto){

        for(User u : userRepository.findAll()){
            if(u.getEmail().equals(userDto.getEmail())){
                userRepository.delete(u);
            }
        }
    }
}
