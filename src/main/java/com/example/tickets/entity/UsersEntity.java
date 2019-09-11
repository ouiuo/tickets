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
@Entity(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(generator = "users_sequence")
    private Long id;

    @Column
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String name;

    @Column(name = "second_name")
    @NotNull
    private String secondName;

    @Column(name = "third_name")
    @NotNull
    private String thirdName;

    @JsonProperty("Gender")
    @OneToOne
    @JoinColumn(name = "gender_id", nullable = false)
    @NotNull
    private GenderEntity genderEntity;

    @JsonProperty("Role")
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private RoleEntity roleEntity;


}
