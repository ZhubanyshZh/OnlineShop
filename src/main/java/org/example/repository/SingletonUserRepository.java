package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
public class SingletonUserRepository{

    private volatile static SingletonUserRepository instance;
    public List<User> users = new ArrayList<>();

    private SingletonUserRepository() {}

    public static SingletonUserRepository getInstance(){
        if(instance == null){
            synchronized (SingletonUserRepository.class){
                if (instance == null){
                    return new SingletonUserRepository();
                }
            }
        }
        return instance;
    }

    public boolean addUser(User user){
        return users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
