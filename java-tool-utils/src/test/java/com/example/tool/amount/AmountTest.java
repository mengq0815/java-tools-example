package com.example.tool.amount;


import java.math.BigDecimal;

public class AmountTest {
    public static void main(String[] args) {
        System.out.println(AmountToBMB.convert("100.01"));

        BigDecimal bigNum1 = new BigDecimal("0.111");
        BigDecimal bigNum2 = new BigDecimal("0.331");
        BigDecimal multiply = BigDecimalUtil.multiply(bigNum1, bigNum2);
        System.out.println(multiply);
        System.out.println(BigDecimalUtil.setScale(multiply, 2));
    }
}