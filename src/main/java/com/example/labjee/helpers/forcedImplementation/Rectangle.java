package com.example.labjee.helpers.forcedImplementation;

public class Rectangle implements Shape {
    double sideA;
    double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double calculatePerimeter() {
        return sideA * 2 + sideB * 2;
    }

    public double calculateArea() {
        return sideA * sideB;
    }
}