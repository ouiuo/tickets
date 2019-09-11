package com.example.tickets.controller;

import com.example.tickets.entity.UsersEntity;
import com.example.tickets.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<UsersEntity> usersEntities = usersService.getAll();
        model.addAttribute("users", usersEntities);

        return "users";
    }

    @GetMapping("/edit/{id}")
    public String changeUsers(@PathVariable Long id, Model model) {
        List<UsersEntity> usersEntities = usersService.getAll();
        model.addAttribute("users", usersEntities);
        model.addAttribute("change", id);

        UsersEntity usersEntity = getUsersById(id);
        model.addAttribute("edit", usersEntity);

        return "users";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    UsersEntity getUsersById(@PathVariable Long id) {
        return usersService.getOne(id);
    }

    @PostMapping("/add")
    private String addNewUser(UsersEntity edit, Model model) {
        usersService.addOrUpdate(edit);

        return "redirect:/users/all";
    }

    @GetMapping("/new")
    public String createUser(Model model){
        List<UsersEntity> usersEntities = usersService.getAll();
        model.addAttribute("users", usersEntities);

        UsersEntity usersEntity = new UsersEntity();
        model.addAttribute("newUser", usersEntity);

        return "users";
    }

    @PostMapping("/update")
    private String updateUser(UsersEntity edit, Model model) {
        usersService.addOrUpdate(edit);

        return "redirect:/users/all";
    }


    @DeleteMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id, Model model) {
        usersService.removeUser(id);

        return "redirect:/users/all";
    }


}
