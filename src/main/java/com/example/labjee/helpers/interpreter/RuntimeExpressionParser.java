package com.example.labjee.helpers.interpreter;

import java.util.ArrayList;
import java.util.Stack;
// Tydzień 5 - wzorzec Interpeter - obsługa interpetera
public class RuntimeExpressionParser {

    Stack<MovieRuntimeExpression> stack = new Stack<>();

    public int parse(String expression) {
        ArrayList<String> tokens = getTokenArray(expression);
        for (String symbol : tokens) {
            if (!isOperator(symbol)) {
                MovieRuntimeExpression numberExpression = new NumberExpression(symbol);
                stack.push(numberExpression);
            } else {
                MovieRuntimeExpression secondExpression;
                secondExpression = symbol.equals("s") ? null : stack.pop();
                MovieRuntimeExpression firstExpression = stack.pop();
                MovieRuntimeExpression operator = getExpressionObject(symbol, firstExpression, secondExpression);
                int result = operator.interpret();
                NumberExpression resultExpression = new NumberExpression(result);
                stack.push(resultExpression);
            }
        }
        return stack.pop().interpret();
    }

    private ArrayList<String> getTokenArray(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> opeTokens = new ArrayList<>();
        for (int i = 0, breakpoint = 0; i < expression.length(); i++) {
            if (isOperator(expression.charAt(i))) {
                tokens.add(expression.substring(breakpoint, i));
                breakpoint = i + 1;
                opeTokens.add(expression.substring(i, i + 1));
            } else if (!isOperator(expression.charAt(i)) && i == expression.length() - 1) {
                tokens.add(expression.substring(breakpoint));
            }
        }
        for (int i = opeTokens.size() - 1; i >= 0; i--) {
            tokens.add(opeTokens.get(i));
        }
        return tokens;
    }

    private boolean isOperator(char symbol) {
        return (symbol == 'h' || symbol == 'm' || symbol == 's' || symbol == ':');
    }

    private boolean isOperator(String symbol) {
        return (symbol.equals("h") || symbol.equals("m") || symbol.equals("s") || symbol.equals(":"));
    }

    private MovieRuntimeExpression getExpressionObject(
                String symbol,
                MovieRuntimeExpression firstExpression,
                MovieRuntimeExpression secondExpression) {
        switch (symbol) {
            case "h":
                return new HourExpression(firstExpression, secondExpression);
            case "m":
                return new MinuteExpression(firstExpression, secondExpression);
            case ":":
                return new DividerExpression(firstExpression, secondExpression);
            default:
                return new SecondExpression(firstExpression);
        }
    }
}
