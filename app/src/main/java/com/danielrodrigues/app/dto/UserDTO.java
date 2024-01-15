package com.danielrodrigues.app.dto;

public class UserDTO {
    private String username;
    private String token;

    public UserDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String username() {
        return this.username;
    }

    public String token() {
        return this.token;
    }
}
