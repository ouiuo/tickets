package com.example.tickets.repository;

import com.example.tickets.entity.UserForAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserForAuthRepository extends JpaRepository<UserForAuth, Long> {
    UserForAuth findByLogin(String login);
}
