package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Shape;

public class Rectangle implements Shape {
    float sideA;
    float sideB;

    public Square(float sideA, float sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    public float calcPerimeter() {
        return sideA * 2 + sideB * 2;
    }

    public float calcArea() {
        return sideA * sideB;
    }
}