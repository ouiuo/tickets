package com.example.tickets.dao.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String userRole;

    UserRole(String userRole){
        this.userRole = userRole;
    }

    public String getName() {
        return userRole;
    }

}
