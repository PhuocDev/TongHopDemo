package com.example.tonghopdemo.entities.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
