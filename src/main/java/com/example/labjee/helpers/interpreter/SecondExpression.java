package com.example.labjee.helpers.interpreter;
// Tydzień 5 - wzorzec Interpeter - obsługa symbolu s
public class SecondExpression implements MovieRuntimeExpression {
    private MovieRuntimeExpression previousExpression;

    public SecondExpression(MovieRuntimeExpression previousExpression) {
        this.previousExpression = previousExpression;
    }
    @Override
    public int interpret() {
        return previousExpression.interpret() * 60;
    }

    @Override
    public String toString() {
        return "s";
    }
}
