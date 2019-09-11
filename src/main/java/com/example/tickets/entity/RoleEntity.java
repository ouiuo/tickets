package com.example.tickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(generator = "role_sequence")
    private Long id;

    @Column
    @NotNull
    private String name;

}
