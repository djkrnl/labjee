package com.example.labjee.helpers.interpreter;
// Tydzień 5 - wzorzec Interpeter - obsługa symbolu numerycznego
public class NumberExpression implements MovieRuntimeExpression {
    private int number;

    public NumberExpression(int number){
        this.number=number;
    }
    public NumberExpression(String number){
        this.number=Integer.parseInt(number);
    }
    @Override
    public int interpret() {
        return this.number;
    }
}
