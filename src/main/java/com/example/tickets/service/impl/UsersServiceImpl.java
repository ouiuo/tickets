package com.example.tickets.service.impl;

import com.example.tickets.dao.enums.UserRole;
import com.example.tickets.entity.UserForAuth;
import com.example.tickets.entity.UsersEntity;
import com.example.tickets.repository.UserForAuthRepository;
import com.example.tickets.repository.UsersRepository;
import com.example.tickets.service.GenderService;
import com.example.tickets.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements com.example.tickets.service.UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private UserForAuthRepository userForAuthRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<UsersEntity> getAll() {
        return usersRepository.findAll();
    }

    public UsersEntity getOne(Long id) {
        Optional<UsersEntity> optional = usersRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new IllegalArgumentException("Wrong users id");
        }

    }


    public UsersEntity addOrUpdate(UsersEntity usersEntity) {
        Long id = usersEntity.getId();

        if (id != null) {
            UsersEntity find = usersRepository.getOne(id);


            find.setLogin(usersEntity.getLogin());
            find.setName(usersEntity.getName());
            find.setSecondName(usersEntity.getSecondName());
            find.setThirdName(usersEntity.getThirdName());
            find.setRoleEntity(usersEntity.getRoleEntity());
            find.setGenderEntity(usersEntity.getGenderEntity());

            System.out.println(find.getPassword());
            System.out.println(usersEntity.getPassword());

            if (find.getPassword() != usersEntity.getPassword()) {
                find.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
            }

            return usersRepository.save(find);
        }
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        return usersRepository.save(usersEntity);
    }


    public void removeUser(Long id) {
        Optional<UsersEntity> optional = usersRepository.findById(id);

        if (optional.isPresent()) {
            usersRepository.delete(optional.get());
        } else {
            throw new IllegalArgumentException("Wrong user id");
        }
    }

    @Override
    public UserForAuth getUserByLogin(String login) {
        return userForAuthRepository.findByLogin(login);
    }

    @Override
    public Long findIdByLogin(String login) {
        return usersRepository.findIdByLogin(login);
    }

    @Override
    public List<UsersEntity> getAll(Principal principal) {
        String userLogin = principal.getName();

        UserForAuth userForAuth = getUserByLogin(userLogin);


        if (userForAuth.getRoleEntity().getId() == 1) {
            return getAll();
        } else {
            List<UsersEntity> entities = new ArrayList<>();
            entities.add(usersRepository.findByLogin(userLogin));

            return entities;
        }
    }


}
