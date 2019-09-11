package com.example.tickets.controller;

import com.example.tickets.entity.TicketEntity;
import com.example.tickets.entity.UsersEntity;
import com.example.tickets.service.TicketService;
import com.example.tickets.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    private String getAll(Principal principal, Model model) {
        List<TicketEntity> ticketEntities = ticketService.getAll(principal);
        model.addAttribute("tickets", ticketEntities);

        return "tickets";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    TicketEntity getUsersById(@PathVariable Long id) {
        return ticketService.getTicketEntity(id);
    }

    @PostMapping("/add")
    public String addTicket(Principal principal, TicketEntity usersEntity) {
        ticketService.addOrUpdateTicket(usersEntity);

        return "redirect:/tickets/all";
    }

    @GetMapping("/edit/{id}")
    public String changeUsers(@PathVariable Long id, Model model, Principal principal) {
        List<TicketEntity> ticketEntities = ticketService.getAll(principal);
        model.addAttribute("tickets", ticketEntities);
        model.addAttribute("change", id);

        TicketEntity ticketEntity = ticketService.getTicketEntity(id);
        model.addAttribute("edit", ticketEntity);

        List<UsersEntity> users = usersService.getAll(principal);
        model.addAttribute("usr", users);

        return "tickets";
    }

    @PostMapping("/update")
    public String updateTicket(TicketEntity edit, Model model) {
        ticketService.addOrUpdateTicket(edit);

        return "redirect:/tickets/all";
    }


    @DeleteMapping("/remove/{id}")
    public void removeTicket(@PathVariable Long id) {
        ticketService.removeTicket(id);
    }

    @GetMapping("/new")
    public String createTicket(Model model, Principal principal) {
        List<TicketEntity> ticketEntities = ticketService.getAll(principal);
        model.addAttribute("tickets", ticketEntities);

        TicketEntity ticketEntity = new TicketEntity();
        model.addAttribute("newTick", ticketEntity);

        List<UsersEntity> users = usersService.getAll(principal);
        model.addAttribute("usr", users);

        return "tickets";
    }


}
