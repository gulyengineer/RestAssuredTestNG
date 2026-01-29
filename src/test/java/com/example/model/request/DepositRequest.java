package com.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data                       // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    private String accountNumber;
    private BigDecimal amount;
    private String description;
}
