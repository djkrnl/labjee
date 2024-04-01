package com.example.labjee.helpers.interpreter;
// Tydzień 5 - wzorzec Interpeter - obsługa symbolu m
public class MinuteExpression implements MovieRuntimeExpression {
    private MovieRuntimeExpression firstExpression, secondExpression;

    public MinuteExpression(MovieRuntimeExpression firstExpression, MovieRuntimeExpression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
    @Override
    public int interpret() {
        return firstExpression.interpret() * 60 + secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "m";
    }
}
