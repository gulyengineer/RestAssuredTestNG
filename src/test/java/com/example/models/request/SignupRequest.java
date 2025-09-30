package com.example.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
}
