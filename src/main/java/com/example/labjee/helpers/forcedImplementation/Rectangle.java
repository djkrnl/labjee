package com.example.labjee.helpers.forcedImplementation;

public class Rectangle implements Shape {
    double sideA;
    double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double calcPerimeter() {
        return sideA * 2 + sideB * 2;
    }

    public double calcArea() {
        return sideA * sideB;
    }
}