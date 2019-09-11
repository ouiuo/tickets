package com.example.tickets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserForAuth {
    @Id
    private Long id;

    @Column
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    @JsonProperty("Role")
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private RoleEntity roleEntity;

}
