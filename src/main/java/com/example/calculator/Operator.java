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
            case "+", ("-"):
                precedence = 1;
                break;
            case "*", "/":
                precedence = 0;
                break;
            default:
                break;
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
