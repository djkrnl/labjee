package com.example.labjee.helpers.forcedImplementation;

public class Square implements Shape {
    double side;

    public Square(double side) {
        this.side = side;
    }

    public double calculatePerimeter() {
        return side * 4;
    }

    public double calculateArea() {
        return side * side;
    }
}