package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByNewsNotification(String NewsNotification);

    User findUsersByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(String email);

    boolean existsByEmailAndPhoneNumber(String email, String phoneNum);
}
