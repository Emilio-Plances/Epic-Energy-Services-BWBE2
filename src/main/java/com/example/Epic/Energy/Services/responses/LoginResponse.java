package com.example.Epic.Energy.Services.responses;

import com.example.Epic.Energy.Services.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private LocalDateTime loginDate;
    private String token;
    private User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
        loginDate=LocalDateTime.now();
    }
}
