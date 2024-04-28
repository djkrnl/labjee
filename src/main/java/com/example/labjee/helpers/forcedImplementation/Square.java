package com.example.labjee.helpers.forcedImplementation;

public class Square implements Shape {
    double side;

    public Square(double side) {
        this.side = side;
    }

    public double calcPerimeter() {
        return side * 4;
    }

    public double calcArea() {
        return side * side;
    }
}