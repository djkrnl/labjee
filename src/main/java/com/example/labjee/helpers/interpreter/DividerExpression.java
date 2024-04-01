package com.example.labjee.helpers.interpreter;
// Tydzień 5 - wzorzec Interpeter - obsługa symbolu :
public class DividerExpression implements MovieRuntimeExpression {

    private MovieRuntimeExpression firstExpression, secondExpression;

    public DividerExpression(MovieRuntimeExpression firstExpression, MovieRuntimeExpression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
    @Override
    public int interpret() {
        return (firstExpression.interpret() * 60 + secondExpression.interpret()) * 60;
    }

    @Override
    public String toString() {
        return ":";
    }
}
