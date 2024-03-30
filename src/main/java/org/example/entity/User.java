package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "ms_user", indexes = {
        @Index(name = "idx_user_email_unq", columnList = "email", unique = true),
        @Index(name = "idx_user_phonenumber_unq", columnList = "phoneNumber", unique = true)
})
public final class User {
    @Id
    @GeneratedValue
    @Column(name = "userId")
    private Long id;


    @Column(name = "userName")
    private String name;

    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "role")
    private String role;

    @Column(name = "BirthdayNotification")
    private boolean BirthdayDiscountNotification;

    @Column(name = "NewCollectionNotification")
    private boolean NewCollectionNotification;

    @Column(name = "DiscountNotification")
    private boolean DiscountNotification;
}
