package com.example.tonghopdemo.entities.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
}
