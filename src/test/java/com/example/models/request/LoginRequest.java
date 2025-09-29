package com.example.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor          // generates no-args constructor
@AllArgsConstructor         // generates all-args constructor

public class LoginRequest {
    private String username;
    private String password;
}
