package com.example.tickets.repository;

import com.example.tickets.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByLogin(String login);
    Long findIdByLogin(String login);
    Long findRole_idByLogin(String login);
}
