package com.example.tickets.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketEntity {
    @Id
    @NotNull
    @GeneratedValue(generator = "ticket_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id", nullable = false)
    private UsersEntity users;

    @Column
    private BigDecimal cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "event_date", columnDefinition="DATE")
    private Date dateTime;


}
