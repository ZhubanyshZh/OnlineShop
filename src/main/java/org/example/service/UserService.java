package org.example.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.dto.ChangePasswordDto;
import org.example.dto.DTO;
import org.example.entity.CustomEntity;
import org.example.entity.User;
import org.example.dto.UserDto;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService extends MyService {

    public UserService(ProductRepository productRepository, UserRepository userRepository, LoggedUserManagementService loggedUserManagementService) {
        super(productRepository, userRepository, loggedUserManagementService);
    }

    public boolean checkUser(String email, String password, Model model) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getUserPassword().equals(password)) {
                loggedUserManagementService.setId(u.getId());
                loggedUserManagementService.setName(u.getName());
                loggedUserManagementService.setPhoneNumber(u.getPhoneNumber());
                loggedUserManagementService.setBirthday(u.getBirthday());
                loggedUserManagementService.setAddress(u.getAddress());
                loggedUserManagementService.setEmail(u.getEmail());
                loggedUserManagementService.setPassword(u.getUserPassword());
                loggedUserManagementService.setRole(u.getRole());
                loggedUserManagementService.setNewsNotification(u.getNewsNotification());


                model.addAttribute("products", productRepository.findAll());
                model.addAttribute("userName", loggedUserManagementService.getName());
                return true;
            }
        }

        return false;
    }
    @Override
    boolean validation(DTO dto, Model model) {
        UserDto userDto = (UserDto) dto;

        String nameError = "", phoneNumError = "", birthdayError = "", addressError = "", emailError = "", passwordError = "";
        if (userDto.getName().isEmpty()) {
            nameError += "name is empty";
            model.addAttribute("nameError", true);
            model.addAttribute("nameErrorContent", nameError);
        } else model.addAttribute("name", userDto.getName());

        if (userDto.getPhoneNumber().isEmpty()) {
            phoneNumError += "phone number is empty";
            model.addAttribute("phNumError", true);
            model.addAttribute("phNumErrorContent", phoneNumError);
        } else model.addAttribute("phoneNum", userDto.getPhoneNumber());

        if (userDto.getPhoneNumber().length() != 11 && !userDto.getPhoneNumber().isEmpty()) {
            phoneNumError += "phone number must have 11 nums";
            model.addAttribute("phNumError", true);
            model.addAttribute("phNumErrorContent", phoneNumError);
        } else model.addAttribute("phoneNum", userDto.getPhoneNumber());

        if (userDto.getBirthday().isEmpty()) {
            birthdayError += "birthday is empty";
            model.addAttribute("birthdayError", true);
            model.addAttribute("birthdayErrorContent", birthdayError);
        } else model.addAttribute("birthday", userDto.getBirthday());

        if (userDto.getAddress().isEmpty()) {
            addressError += "address is empty";
            model.addAttribute("addressError", true);
            model.addAttribute("addressErrorContent", addressError);
        } else model.addAttribute("address", userDto.getAddress());

        if (userDto.getEmail().isEmpty()) {
            emailError += "email is empty";
            model.addAttribute("emailError", true);
            model.addAttribute("emailErrorContent", emailError);
        } else model.addAttribute("email", userDto.getEmail());

        if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
            emailError += "email isn't valid";
            model.addAttribute("emailError", true);
            model.addAttribute("emailErrorContent", emailError);
        } else model.addAttribute("email", userDto.getEmail());

        if (userDto.getPassword().isEmpty()) {
            passwordError += "password is empty";
            model.addAttribute("passwordError", true);
            model.addAttribute("passwordErrorContent", passwordError);
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
                model.addAttribute("passwordError", true);
                model.addAttribute("passwordErrorContent", passwordError);
            } else if (!matcher2.find()) {
                passwordError += "Пароль должен содержать буквы A-Z";
                model.addAttribute("passwordError", true);
                model.addAttribute("passwordErrorContent", passwordError);
            } else if (!matcher3.find()) {
                passwordError += "Пароль должен содержать буквы a-z";
                model.addAttribute("passwordError", true);
                model.addAttribute("passwordErrorContent", passwordError);
            } else if (!matcher4.find()) {
                passwordError += "Пароль должен содержать знаки";
                model.addAttribute("passwordError", true);
                model.addAttribute("passwordErrorContent", passwordError);
            } else if (userDto.getPassword().length() <= 8) {
                passwordError += "Пароль должен содержать 8 символов";
                model.addAttribute("passwordError", true);
                model.addAttribute("passwordErrorContent", passwordError);
            }

            model.addAttribute("password", userDto.getPassword());
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



    public boolean changeUser(UserDto userDto) {
        try {
            User user = userRepository.findById(userDto.getId()).get();

            setUserDtoToUser(user, userDto);
            userRepository.save(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    boolean haveSuchTuple(DTO dto, Model model){
        UserDto userDto = (UserDto) dto;
        List<User> users = userRepository.findAll();

        boolean haveAlreadyAccount = users.stream()
                .anyMatch(user -> user.getEmail().equals(userDto.getEmail()) ||
                        user.getPhoneNumber().equals(userDto.getPhoneNumber()));

        if (haveAlreadyAccount) model.addAttribute("alreadyHave", true);

        return haveAlreadyAccount;
    }

    private void setUserDtoToUser(User user, UserDto userDto) {
        user.setName(userDto.getName().trim());
        user.setBirthday(userDto.getBirthday());
        user.setAddress(userDto.getAddress().trim());
        user.setRole(userDto.getRole());
    }

    public boolean changePassword(ChangePasswordDto changePasswordDto, Model model) {

        Optional<User> optionalUser = userRepository.findById(changePasswordDto.getId());
        User user = optionalUser.get();
        if (!user.getUserPassword().equals(changePasswordDto.getOldPassword())) {
            loggedUserManagementService.setMessageToUser("Old password not confirm!");
            return false;
        }

        if (PasswordValidation(changePasswordDto)) return false;

        try {
            user.setUserPassword(changePasswordDto.getNewPassword());
            userRepository.save(user);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean changeNotify(UserDto userDto){
        User user = userRepository.findById(loggedUserManagementService.getId()).get();

        if(userDto.getNewsNotification() == null){
            user.setNewsNotification("false");
            loggedUserManagementService.setNewsNotification("false");
        }else {
            user.setNewsNotification("true");
            loggedUserManagementService.setNewsNotification("true");
        }

        try{
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkCode(String code) {
        try{
            Long parseLong = Long.parseLong(code);
            if(loggedUserManagementService.getCodeToConfirmEmail().equals(parseLong)){
                loggedUserManagementService.setId(userRepository.findUserByEmail(loggedUserManagementService.getEmail()).getId());
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public boolean createNewPassword(ChangePasswordDto changePasswordDto, Model model) {
        try{
            if(changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())){
                if (PasswordValidation(changePasswordDto)) return false;

                try{
                    User user = userRepository.findById(loggedUserManagementService.getId()).get();

                    user.setUserPassword(changePasswordDto.getNewPassword());
                    userRepository.save(user);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
                return true;
            }else{
                loggedUserManagementService.setMessageToUser("Пароли не совподают");
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    protected CustomEntity dtoToEntity(DTO dto) {
        User user = new User();
        UserDto userDto = (UserDto) dto;

        user.setName(userDto.getName().trim());
        user.setPhoneNumber(userDto.getPhoneNumber().trim());
        user.setBirthday(userDto.getBirthday());
        user.setAddress(userDto.getAddress().trim());
        user.setEmail(userDto.getEmail().trim());
        user.setUserPassword(userDto.getPassword().trim());
        user.setRole("user");
        user.setNewsNotification("false");

        return user;
    }

    private boolean PasswordValidation(ChangePasswordDto changePasswordDto) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher1 = pattern.matcher(changePasswordDto.getNewPassword());

        pattern = Pattern.compile("[A-Z]");
        Matcher matcher2 = pattern.matcher(changePasswordDto.getNewPassword());

        pattern = Pattern.compile("[a-z]");
        Matcher matcher3 = pattern.matcher(changePasswordDto.getNewPassword());

        pattern = Pattern.compile("[.,:;@&%$]");
        Matcher matcher4 = pattern.matcher(changePasswordDto.getNewPassword());

        if (!matcher1.find()) {
            loggedUserManagementService.setMessageToUser("password must have nums [0-9]");
            return true;
        }
        if (!matcher2.find()) {
            loggedUserManagementService.setMessageToUser("Пароль должен содержать буквы A-Z");
            return true;
        }
        if (!matcher3.find()) {
            loggedUserManagementService.setMessageToUser("Пароль должен содержать буквы a-z");
            return true;
        }
        if (!matcher4.find()) {
            loggedUserManagementService.setMessageToUser("Пароль должен содержать знаки");
            return true;
        }
        if (changePasswordDto.getNewPassword().length() <= 8) {
            loggedUserManagementService.setMessageToUser("Пароль должен содержать 8 символов");
            return true;
        }

        if (!changePasswordDto.getConfirmPassword().equals(changePasswordDto.getNewPassword())) {
            loggedUserManagementService.setMessageToUser("Новые пароли не совпадает!");
            return true;
        }
        return false;
    }

//    public boolean deleteUser(Long id) {
//        try {
//            userRepository.deleteById(id);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return false;
//        }
//        return true;
//    }

//    public User userDtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setNewsNotification("false");
//        user.setRole("user");
//        user.setUserPassword(userDto.getPassword().trim());
//        user.setEmail(userDto.getEmail().trim());
//        user.setAddress(userDto.getAddress().trim());
//        user.setBirthday(userDto.getBirthday());
//        user.setPhoneNumber(userDto.getPhoneNumber().trim());
//        user.setName(userDto.getName().trim());
//        return user;
//    }

//    public boolean addUser(UserDto userDto, Model model) {
//
//        if (validation(userDto, model) && !haveSuchTuple(userDto, model)) {
//            User user = (User) dtoToEntity(userDto);
//            try {
//                userRepository.save(user);
//                model.addAttribute("loggedSuccess", true);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                return false;
//            }
//            return true;
//        }
//        return false;
//    }
}
