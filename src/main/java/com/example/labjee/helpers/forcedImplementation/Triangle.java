package com.example.labjee.helpers.forcedImplementation;

import java.lang.Math;

public class Triangle implements Shape {
    double side;

    public Triangle(double side) {
        this.side = side;
    }

    public double calculatePerimeter() {
        return side * 3;
    }

    public double calculateArea() {
        return side * side * Math.sqrt(3) / 4;
    }
}