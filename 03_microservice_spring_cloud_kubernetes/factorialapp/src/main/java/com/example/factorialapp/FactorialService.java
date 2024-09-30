package com.example.factorialapp;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FactorialService {
    public BigDecimal calculate(BigDecimal n) {
        if (n.equals(BigDecimal.ZERO)) {
            return BigDecimal.ONE;
        }
        return n.multiply(calculate(n.subtract(BigDecimal.ONE)));
    }
}
