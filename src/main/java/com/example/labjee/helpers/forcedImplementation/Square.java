package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Shape;

public class Square implements Shape {
    float side;

    public Square(float side) {
        this.side = side;
    }

    public float calcPerimeter() {
        return side * 4;
    }

    public float calcArea() {
        return side * side;
    }
}