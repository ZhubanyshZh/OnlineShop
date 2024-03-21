package org.example.repository;

import org.example.entity.LoginLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLoggerRepository extends JpaRepository<LoginLogger, Long> {
}
