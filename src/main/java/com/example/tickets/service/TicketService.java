package com.example.tickets.service;

import com.example.tickets.entity.TicketEntity;

import java.security.Principal;
import java.util.List;

public interface TicketService {
    TicketEntity getTicketEntity(Long id);

    List<TicketEntity> getAll();

    TicketEntity addOrUpdateTicket(TicketEntity ticketEntity, Principal principal);

    TicketEntity addOrUpdateTicket(TicketEntity ticketEntity);

    void removeTicket(Long id);

    List<TicketEntity> getAll(Principal principal);

}
