package org.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String birthday;
    private String address;
    private String email;
    private String password;
    private boolean BirthdayDiscountNotification = false;
    private boolean NewCollectionNotification = false;
    private boolean DiscountNotification = false;
}