package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.LoginLogger;
import org.example.repository.LoginLoggerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginLoggerService {

    private final LoginLoggerRepository loginLoggerRepository;

    public LoginLogger saveLog(LoginLogger loginLogger){
        return loginLoggerRepository.save(loginLogger);
    }

}
