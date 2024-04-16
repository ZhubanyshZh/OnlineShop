package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ms_signUp_logger")
public class LoginLogger {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "args")
    private String args;

    @Column(name = "response")
    private String response;

    @Column(name = "time")
    private String createdAt;
}