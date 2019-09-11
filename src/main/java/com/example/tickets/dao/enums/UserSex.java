package com.example.tickets.dao.enums;

public enum UserSex {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNDEFINED("UNDEFINED");

    private final String userSex;

    UserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getName(){
        return userSex;
    }
}
