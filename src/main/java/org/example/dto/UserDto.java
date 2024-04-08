package org.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String address;
    private String email;
    private String password;
    private String role;
    private String NewsNotification;
}