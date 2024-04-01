package com.example.labjee.helpers.interpreter;
// Tydzień 5 - wzorzec Interpeter - obsługa symbolu h
public class HourExpression implements MovieRuntimeExpression {
    private MovieRuntimeExpression firstExpression, secondExpression;

    public HourExpression(MovieRuntimeExpression firstExpression, MovieRuntimeExpression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
    @Override
    public int interpret() {
        return firstExpression.interpret() * 3600 + secondExpression.interpret();
    }

    @Override
    public String toString() {
        return "h";
    }
}
