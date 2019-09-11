package com.example.tickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "gender")
public class GenderEntity {
    @Id
    @GeneratedValue(generator = "gender_sequence")
    private Long id;

    @Column
    @NotNull
    private String name;

}
