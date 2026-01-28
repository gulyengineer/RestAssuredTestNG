package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {
    private String accountNumber;
    private double amount;
    private String description;
}
