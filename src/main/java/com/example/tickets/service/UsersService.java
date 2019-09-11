package com.example.tickets.service;

import com.example.tickets.entity.UserForAuth;
import com.example.tickets.entity.UsersEntity;

import java.security.Principal;
import java.util.List;

public interface UsersService {

    List<UsersEntity> getAll();

    List<UsersEntity> getAll(Principal principal);

    UsersEntity getOne(Long id);

    UsersEntity addOrUpdate(UsersEntity usersEntity);

    void removeUser(Long id);

    Long findIdByLogin(String login);

    UserForAuth getUserByLogin(String login);


}
