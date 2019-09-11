package com.example.tickets.service.impl;

import com.example.tickets.entity.TicketEntity;
import com.example.tickets.entity.UserForAuth;
import com.example.tickets.entity.UsersEntity;
import com.example.tickets.repository.TicketRepository;
import com.example.tickets.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements com.example.tickets.service.TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UsersService usersService;
    //TODO change find by id => find by login
    @Override
    public List<TicketEntity> getAll(Principal principal) {
        String login = principal.getName();

        UserForAuth userForAuth = usersService.getUserByLogin(login);

        if (hasAccess(userForAuth)) {
            return getAll();
        } else {
            return ticketRepository.findByUsers_id(userForAuth.getId());
        }
    }

    @Override
    public TicketEntity getTicketEntity(Long id) {
        Optional<TicketEntity> optional = ticketRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new IllegalArgumentException("Wrong ticket id");
        }
    }

    @Override
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }

    //TODO change create and update methods

    @Override
    public TicketEntity addOrUpdateTicket(TicketEntity ticketEntity) {
        UsersEntity usersEntity = ticketEntity.getUsers();

        return ticketRepository.save(ticketEntity);
    }

    @Override
    public TicketEntity addOrUpdateTicket(TicketEntity ticketEntity, Principal principal) {
        UsersEntity users = ticketEntity.getUsers();

        String login = principal.getName();

        UserForAuth userForAuth = usersService.getUserByLogin(login);

        if (hasAccess(userForAuth) || users.getId() == usersService.findIdByLogin(principal.getName())) {
            return addOrUpdateTicket(ticketEntity);
        }
        throw new IllegalArgumentException("You have no access");
    }

    @Override
    public void removeTicket(Long id) {
        Optional<TicketEntity> optional = ticketRepository.findById(id);

        if (optional.isPresent()) {
            ticketRepository.delete(optional.get());
        } else {
            throw new IllegalArgumentException("Wrong ticket id");
        }
    }

    private boolean hasAccess(UserForAuth user) {
        return user.getRoleEntity().getName().equals("ADMIN");
    }


}
