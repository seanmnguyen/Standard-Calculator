package com.example.calculator;

public class Operator
{
    private String sign;
    private int precedence;
    public Operator(String sign)
    {
        this.sign = sign;
        switch(sign)
        {
            case "+", ("-") -> precedence = 2;
            case "*", "/" -> precedence = 1;
            case "^" -> precedence = 0;
            default -> System.out.println("invalid operator");
        }
    }

    public String getSign()
    {
        return sign;
    }

    public int getPrecedence()
    {
        return precedence;
    }
}
