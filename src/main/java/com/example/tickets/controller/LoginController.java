package com.example.tickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = {"/login.html","/",""})
    public String login(){
        return "login";
    }

}
