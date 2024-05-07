package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ms_user", indexes = {
        @Index(name = "idx_user_email_unq", columnList = "email", unique = true),
        @Index(name = "idx_user_phonenumber_unq", columnList = "phoneNumber", unique = true)
})
public class User implements CustomEntity, UserDetails {
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

    @Column(name = "newsNotification")
    private String newsNotification;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


